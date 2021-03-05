## REST API 
  #### Online File Manager like the Google Drive is an open-source Java web application that enables users to virtually manage their files and folders


## Tools

  - MAVEN
  - JPA/Hibernate
  - Spring Boot Framework
  - MySQL Database

## Features
  - Create parent folder(s) and sub-folders.
  -  Navigate through folders and sub-folder.
    *[1] Delete a folder
    *[2] Upload file(s)
    *[3] Delete a file
 -   Download file(s)
 -   Download a specific version of a file.
 -   Deleting a folder that includes files and nested sub-folder(s) will delete all its subfolder(s).
 -   If a file with the same name already exists in a folder, a new version of the file will be created
 -   All versions of a file will be deleted.

  ## Endpoints
    - All of the functionalities can be tested using Postman.
    - The input and output are designed to be in JSON format.
    - URL patterns are listed as below:
    - List all top-level files and folders --> http://localhost:8080 GET
    - List all files and folders in an existing folder --> http://localhost:8080/folders/{folderId} GET
    - Get a folder's parent folder's name --> http://localhost:8080/folders/parentName/{folderId} GET
    - Get a folder's parent folder's id --> http://localhost:8080/folders/parentId/{folderId} GET
    - Create a new top-level folder --> http://localhost:8080/folders POST
    - Create a new folder in an existing folder --> http://localhost:8080/folders POST
    - Upload a file to the top level (i.e. no parent folder) --> http://localhost:8080/files POST
    - Upload a file to an existing folder - no file in the folder with the same name --> http://localhost:8080/folders/{folderId}/files POST
    - Download a specific version of a file --> http://localhost:8080/files/{fileId}/versions/{versionNumber} GET
    - Download a file without specifying a version --> http://localhost:8080/files/{fileId} GET
    - Delete a file --> http://localhost:8080/files/{fileId} DELETE
    - Delete a folder --> http://localhost:8080/folders/{folderId} DELETE
