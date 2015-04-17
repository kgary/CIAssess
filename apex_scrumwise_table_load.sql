/* Load data from scrumwise xml into appropriate tables using Xpath */
INSERT INTO persons(person_id, first_name, last_name, email)
SELECT person_id,
       first_name,
       last_name,
       email
FROM xmltable('/scrumwise-export/data/account/persons/person' passing xmltype(bfilename('TEST_DIR1', 'SCRUM.xml'), nls_charset_id('AL32UTF8')) columns person_id varchar2(50) path 'id', first_name varchar2(100) path 'first-name', last_name varchar2(100) path 'last-name', email varchar2(320) path 'email-address') ;


INSERT INTO projects(project_id, project_name, project_description)
SELECT project_id,
       project_name,
       project_description
FROM xmltable('/scrumwise-export/data/account/projects/project' passing xmltype(bfilename('TEST_DIR1', 'SCRUM.xml'), nls_charset_id('AL32UTF8')) columns project_id varchar2(50) path 'id', project_name varchar2(500) path 'name', project_description varchar2(3000) path 'description') ;


INSERT INTO teams(project_id, team_id, team_name, person_id)
SELECT proj.project_id,
       team.team_id,
       team.team_name,
       person.person_id
FROM xmltable('/scrumwise-export/data/account/projects/project' passing xmltype(bfilename('TEST_DIR1', 'SCRUM.xml'), nls_charset_id('AL32UTF8')) columns project_id varchar2(100) path 'id', teams xmltype path 'people/teams/team' /* Specify Xpath to teams tag */ ) proj,
     xmltable('/team' passing proj.teams columns team_id varchar2(100) path 'id', team_name varchar2(100) path 'name', members xmltype path 'team-memberships/team-membership') team,
     xmltable('/team-membership' passing team.members columns person_id varchar2(100) path 'person-id') person;


INSERT INTO sprints(sprint_id, sprint_name, sprint_description, sprint_start_date, sprint_end_date, sprint_board_id, sprint_status, team_id, sprint_availble_effort)
SELECT sprint.sprint_id,
       sprint.sprint_name,
       sprint.sprint_description,
       sprint.sprint_start_date,
       sprint.sprint_end_date,
       sprint.sprint_board_id,
       sprint.sprint_status,
       team.team_id,
       team.available_effort
FROM xmltable('/scrumwise-export/data/account/projects/project/sprints/sprint' passing xmltype(bfilename('TEST_DIR1', 'SCRUM.xml'), nls_charset_id('AL32UTF8')) columns sprint_id varchar2(50) path 'id', sprint_name varchar2(100) path 'name', sprint_description varchar2(50) path 'description', sprint_start_date varchar2(50) path 'start-date', sprint_end_date varchar2(50) path 'end-date', sprint_board_id varchar2(50) path 'board-id', sprint_status varchar2(50) path 'status', persons xmltype path 'team-sprint-participations/team-sprint-participation') sprint,
     xmltable('/team-sprint-participation' passing sprint.persons columns team_id varchar2(50) path 'team-id', available_effort varchar2(10) path 'available-effort', members xmltype path 'team-memberships/team-membership') team ;


INSERT INTO backlog_items(project_id, backlog_item_id, backlog_item_name, backlog_item_description, backlog_item_priority, backlog_item_creator_id, backlog_item_status, backlog_item_remaining_effort, backlog_item_sprint_id, backlog_item_team_id, backlog_item_board_id, backlog_item_person_id)
SELECT proj.project_id,
       backlog.backlog_item_id,
       backlog.backlog_item_name,
       backlog.backlog_item_description,
       backlog.backlog_item_priority,
       backlog.backlog_item_creator_id,
       backlog.backlog_item_status,
       backlog.backlog_item_remaining_effort,
       backlog.backlog_item_sprint_id,
       backlog.backlog_item_team_id,
       backlog.backlog_item_board_id,
       persons.backlog_item_person_id
FROM xmltable ('/scrumwise-export/data/account/projects/project' passing xmltype(bfilename('TEST_DIR1', 'SCRUM.xml'), nls_charset_id('AL32UTF8')) columns project_id varchar2(100) path 'id', backlog xmltype path 'backlog/backlog-items/backlog-item') proj,
     xmltable ('/backlog-item' passing proj.backlog columns backlog_item_id varchar2(50) path 'id', backlog_item_name varchar2(100) path 'name', backlog_item_description varchar2(3000) path 'description', backlog_item_priority varchar2(10) path 'priority', backlog_item_creator_id varchar2(50) path 'creator-id', backlog_item_status varchar2(50) path 'status', backlog_item_remaining_effort varchar2(10) path 'remaining-effort', backlog_item_sprint_id varchar2(50) path 'sprint-id', backlog_item_team_id varchar2(50) path 'team-id', backlog_item_board_id varchar2(50) path 'board-id', persons xmltype path 'assigned-persons') backlog,
     xmltable ('/assigned-persons' passing backlog.persons columns backlog_item_person_id varchar2(100) path 'person-id') persons ;


INSERT INTO backlog_tasks(project_id, backlog_item_id, backlog_task_id, backlog_task_name, backlog_task_description, backlog_task_status, backlog_task_remaining_effort, backlog_task_person_id)
SELECT proj.project_id,
       backlog_item.backlog_item_id,
       backlog_task.backlog_task_id,
       backlog_task.backlog_task_name,
       backlog_task.backlog_task_description,
       backlog_task.backlog_task_status,
       backlog_task.backlog_task_remaining_effort,
       persons.person_id
FROM xmltable ('/scrumwise-export/data/account/projects/project' passing xmltype(bfilename('TEST_DIR1', 'SCRUM.xml'), nls_charset_id('AL32UTF8')) columns project_id varchar2(100) path 'id', backlog_item xmltype path 'backlog/backlog-items/backlog-item') proj,
     xmltable ('/backlog-item' passing proj.backlog_item columns backlog_item_id varchar2(50) path 'id', backlog_task xmltype path 'tasks/task') backlog_item,
     xmltable ('/task' passing backlog_item.backlog_task columns backlog_task_id varchar2(50) path 'id', backlog_task_name varchar2(500) path 'name', backlog_task_description varchar2(3000) path 'description', backlog_task_status varchar2(50) path 'status', backlog_task_remaining_effort varchar2(10) path 'remaining-effort', persons xmltype path 'assigned-persons') backlog_task,
     xmltable ('/assigned-persons' passing backlog_task.persons columns person_id varchar2(100) path 'person-id') persons ;
