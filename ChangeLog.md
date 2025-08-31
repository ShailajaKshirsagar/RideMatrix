# Changelog
All notable changes to this project are documented in this file.

## [0.1.1] - 2025-08-31
### Added
- Create vehicle api added .[VEHMS-M01-T0015]
- Vehicle DTO for saving vehicle with resident id.
- Validation for vehicle registration number.
- Tested and pushed. [VEHMS-M01-T0016]

### Testing
- Tested complete application and bugs resolved. [VEHMS-M01-T0014]
### Bugs resolved 
- Endpoint naming non standard changed .
- Validation added on nested vehicles.
- Email validation added to avoid invalid email format.
- Flat no , mobile no format validated.

### Added
- API GetresidentByName implemented , Tested and Pushed on github[VEHMS-M01-T0013]

- API Implemented For get resident by name.[VEHMS-M01-T0012]
  - validation provided using Pattern and regex
  - Error msg added if resident does not exist
  
## [0.1.0] - 2025-08-30
### Added
- Getallresident API Tested and pushed. [VEHMS-M01-T0011]
- Get all resident API implemented. [VEHMS-M01-T0010]
- Tested Swagger doc and pushed on Git.[VEHMS-M01-T009]
- Swagger documentation added.[VEHMS-M01-T008]
    - @Operation — Describe what the API does
    - @Parameter — Describe API method parameters
    - @Tag — Group related APIs

- API implemented to create Resident with Vehicals.[VEHMS-M01-T007]
    - Global Exception Added for mandatory feilds
    - Not null validation added for enum
    - 
- Tables created with mapping one to many.[VEHMS-M01-T006]
- Implemented Entities Resident and Vehicle .[VEHMS-M01-T005]
- Connection established properly without any error.[VEHMS-M01-T004]
- Spring Boot datasource configured for PostgreSQL (JDBC URL, username, password, PostgreSQL dialect). [VEHMS-M01-T003]
- Local PostgreSQL setup and pgAdmin verification; database ridematrix_vms_db created. [VEHMS-M01-T002]
- Created RideMatrix Spring Boot project. [VEHMS-M01-T001]

