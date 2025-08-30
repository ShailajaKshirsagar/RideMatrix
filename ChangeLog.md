# Changelog
All notable changes to this project are documented in this file.

## [0.1.0] - 2025-08-30
### Added
- Created RideMatrix Spring Boot project. [VEHMS-M01-T001]
- Local PostgreSQL setup and pgAdmin verification; database ridematrix_vms_db created. [VEHMS-M01-T002]
- Spring Boot datasource configured for PostgreSQL (JDBC URL, username, password, PostgreSQL dialect). [VEHMS-M01-T003]
- Connection established properly without any error.[VEHMS-M01-T004]
- Implemented Entities Resident and Vehicle .[VEHMS-M01-T005]
- Tables created with mapping one to many.[VEHMS-M01-T006]
- API implemented to create Resident with Vehicals.[VEHMS-M01-T006]
    - Global Exception Added for mandatory feilds
    - Not null validation added for enum