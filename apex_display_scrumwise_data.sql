/* Apex query to display reports based on user name entered
 display team name,project that the user belongs to, backlog items assigned to his/her name ans relevant status of the same 
 Also display column indicating previous work effort remainaing and change noticed most recently (Previous_Remaining_Effort) */

 select team.team_name , proj.project_name Project_Name ,bck.backlog_item_name Backlog_Item_Name, 
 bck.backlog_item_status Status , bck.backlog_item_remaining_effort Remaining_Effort_in_Hours, 
 bck1.backlog_item_remaining_effort Previous_Remaining_Effort 
from projects proj, BACKLOG_ITEMS bck ,teams team ,BACKLOG_ITEMS bck1 ,persons per 
where 
proj.project_id=team.project_id and 
proj.project_id=bck.PROJECT_ID and 
proj.project_id=bck1.PROJECT_ID and 
bck.project_id=team.project_id and 
bck.backlog_item_id=bck1.backlog_item_id and 
bck.BACKLOG_ITEM_PERSON_ID=per.person_id and
team.project_id=bck1.project_id  and 
team.person_id=per.person_id and 
bck1.BACKLOG_ITEM_PERSON_ID=per.person_id and 
 (lower(per.FIRST_NAME) like lower(:P1_NAME) /* P1_Name variable that holds user input */
 or LOWER(per.LAST_NAME) like lower(:P1_NAME)) /* this part fetches data based on first or last name entered */
group by team.team_name , proj.project_name ,bck.backlog_item_name, bck.backlog_item_status  ,
bck.backlog_item_remaining_effort,bck1.backlog_item_remaining_effort