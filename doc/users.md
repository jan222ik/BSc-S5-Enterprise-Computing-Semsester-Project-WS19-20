## Roles
| Rolename | Read messages | Write Messages  | 
|----------|---------------|-----------------|
| ADMIN    | YES           | YES             | 
| WRITER   | YES           | YES             |
| READER   | YES           | NO              |
| NOBODY   | NO            | NO              | 


## Users

### Backdoor
| Username | Password    | Role  | Topics              |
|----------|-------------|-------|---------------------|
| backdoor | backdoorPWD | ADMIN | SYSTEM, ROCK, OPERA, THEATER |

### FHV User
| Username | Role     | Topics              |
|----------|----------|---------------------|
| ANY      | MESSAGER | SYSTEM, ROCK, OPERA |
| tftest   |          |                     |
| tftest2  |  READER  |   SYSTEM            |

