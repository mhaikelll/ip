# Huhuhuharis User Guide

<img width="401" alt="UI" src="https://github.com/user-attachments/assets/e1367587-7cf9-4d41-92a9-f3e03d0e26f4" />

Introducing Huhuhuharis, your personalised task management chatbot! Huhuhuharis assists with efficient task management using simple and hassle-free commands. Whether its adding or deleting tasks, marking them as done, or setting varying priorities, Huhuhuharis always got you covered. To use Huhuhuharis, simply enter a command in the chatbot interface. Listed below are the available commands and their usages.

## Features

1. List tasks
2. Add todo task
3. Add deadline task
4. Add event task
5. Mark task
6. Unmark task
7. Delete task
8. Find task
9. Set task priority
10. Exit chatbot

## Feature 1: List tasks

Command: ```list``` 
Usage: Displays all the tasks in the list.

## Feature 2: Add todo task

Command: ```list``` <description> 
Usage: Adds a todo task to the list.

## Feature 3: Add deadline task

Command: ```list``` <description> /by <yyyy-MM-dd HHmm>
Usage: Adds a deadline task to the list.

## Feature 4: Add event task

Command: ```list``` <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
Usage: Adds an event task to the list.

## Feature 5: Mark task

Command: ```list``` <taskId>
Usage: Marks the indicated task as done.

## Feature 6: Unmark task

Command: ```list``` <taskId>
Usage: Unmarks the indicated task.

## Feature 7: Delete task

Command: ```list``` <taskId>
Usage: Deletes the indicated task.

## Feature 8: Find task

Command: ```find``` <keyword> 
Usage: Displays all tasks containing the indicated keyword.

## Feature 9: Set task priority

Command: ```list``` <taskId> <High/Medium/Low>
Usage: Sets the indicated task to the specified priority.

## Feature 10: Exit chatbot

Command: ```bye``` 
Usage: Exits the session.

## Persistence

All tasks are automatically saved upon ending the session and subsequently loaded back in when a new session begins. Huhuhuharis ensures your tasks won't be lost.
