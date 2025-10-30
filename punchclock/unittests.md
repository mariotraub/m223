| Name                         | Params     | Result                        |
|------------------------------|------------|-------------------------------|
| Add Entry                    | Entry JSON | 200 - OK + Created Entry JSON |
| Delete Entry                 | id         | 204 - NO CONTENT              |
| Update Entry                 | Entry JSON | 200 - OK + Updated Entry JSON |
| Delete Entry that not exists | id         | 404 - NOT FOUND               |
| Update Entry that not exists | Entry JSON | 404 - NOT FOUND               |