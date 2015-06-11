/*

Apex query to display reports based on user name entered

display team name, project that the user belongs to,

backlog items assigned to his/her name ans relevant status of the same

Also display column indicating previous work effort remainaing

and change noticed most recently (Previous_Remaining_Effort)

*/
SELECT team.team_name,
       proj.project_name project_name,
       bck.backlog_item_name backlog_item_name,
       bck.backlog_item_status status,
       bck.backlog_item_remaining_effort remaining_effort_in_hours,
       bck1.backlog_item_remaining_effort previous_remaining_effort
FROM projects proj,
     backlog_items bck,
     teams team,
     backlog_items bck1,
     persons per
WHERE proj.project_id=team.project_id
    AND proj.project_id=bck.project_id
    AND proj.project_id=bck1.project_id
    AND bck.project_id=team.project_id
    AND bck.backlog_item_id=bck1.backlog_item_id
    AND bck.backlog_item_person_id=per.person_id
    AND team.project_id=bck1.project_id
    AND team.person_id=per.person_id
    AND bck1.backlog_item_person_id=per.person_id
    AND (lower(per.first_name) LIKE lower(:P1_NAME) /* P1_Name variable that holds user input */
         OR lower(per.last_name) LIKE lower(:P1_NAME))/* this part fetches data based on first or last name entered */
GROUP BY team.team_name,
         proj.project_name,
         bck.backlog_item_name,
         bck.backlog_item_status,
         bck.backlog_item_remaining_effort,
         bck1.backlog_item_remaining_effort
