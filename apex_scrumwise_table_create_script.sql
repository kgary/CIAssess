/*

Create distinct tables Persons, Projects, Teams, backlog_items, backlog_tasks and sprints to track data received from scrumwise

Rename backlog_item and backlog_tasks tables to maintain legacy data

*/
ALTER TABLE backlog_items RENAME TO backlog_items_prev;


ALTER TABLE backlog_tasks RENAME TO backlog_tasks_prev;


DROP TABLE backlog_items purge;


DROP TABLE backlog_tasks purge;


CREATE TABLE persons (person_id varchar2(20), first_name varchar2(50), last_name varchar2(50), email varchar2(320));


CREATE TABLE projects (project_id varchar2(50), project_name varchar2(1000), project_description varchar2(3000));


CREATE TABLE teams (project_id varchar2(50), team_id varchar2(50), team_name varchar2(500), person_id varchar2(50));


CREATE TABLE backlog_items (project_id varchar2(50), backlog_item_id varchar2(50), backlog_item_name varchar2(500), backlog_item_description varchar2(3000), backlog_item_priority varchar2(20), backlog_item_creator_id varchar2(50), backlog_item_status varchar2(50), backlog_item_remaining_effort int, backlog_item_sprint_id varchar2(20), backlog_item_team_id varchar2(20), backlog_item_board_id varchar2(20), backlog_item_person_id varchar2(50));


CREATE TABLE backlog_tasks (project_id varchar2(50), backlog_item_id varchar2(50), backlog_task_id varchar2(50), backlog_task_name varchar2(1000), backlog_task_description varchar2(3000), backlog_task_status varchar2(20), backlog_task_remaining_effort varchar2(20), backlog_task_person_id varchar2(50));


CREATE TABLE sprints (sprint_id varchar2(50), sprint_name varchar2(500), sprint_description varchar2(500), sprint_start_date varchar2(50), sprint_end_date varchar2(50), sprint_board_id varchar2(50), sprint_status varchar2(20), team_id varchar2(50), sprint_availble_effort varchar2(10));
