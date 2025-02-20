# Huhuhuharis User Guide

<img width="400" alt="UI" src="https://github.com/user-attachments/assets/77e62ac0-26b5-45d1-a4c9-82efaff98ce1" />

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

Command: ```todo <description>```

Example: ```todo eat breakfast```

Usage: Adds a todo task to the list.

## Feature 3: Add deadline task

Command: ```deadline <description> /by <yyyy-MM-dd HHmm>```

Example: ```deadline finish homework /by 2025-02-23 2359```

Usage: Adds a deadline task to the list.

## Feature 4: Add event task

Command: ```event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>```

Example: ```event project meeting /from 2025-02-23 1600 /to 2025-02-23 1800```

Usage: Adds an event task to the list.

## Feature 5: Mark task

Command: ```mark <taskId>```

Example: ```mark 1```

Usage: Marks the indicated task as done.

## Feature 6: Unmark task

Command: ```unmark <taskId>```

Example: ```unmark 2```

Usage: Unmarks the indicated task.

## Feature 7: Delete task

Command: ```delete <taskId>```

Example: ```delete 3```

Usage: Deletes the indicated task.

## Feature 8: Find task

Command: ```find <keyword>```

Example: ```find homework```

Usage: Displays all tasks containing the indicated keyword.

## Feature 9: Set task priority

Command: ```priority <taskId> <High/Medium/Low>```

Example: ```priority 3 High```

Usage: Sets the indicated task to the specified priority.

## Feature 10: Exit chatbot

Command: ```bye``` 

Usage: Exits the session.

## Persistence

All tasks are automatically saved upon ending the session and subsequently loaded back in when a new session begins. Huhuhuharis ensures your tasks won't be lost.
