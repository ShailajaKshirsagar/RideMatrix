# Changelog
All notable changes to this project are documented in this file.
## [0.1.0] - 2025-08-1

## [0.0.1] - 2025-08-30

### Added
- API to get user details by registration number. [VEHMS-M01-T0017]
  - Resident DTO created.
  - Custom and global exceptions added.
  - Validation on registration number.
- API to create a vehicle with resident ID. [VEHMS-M01-T0015]
  - Vehicle DTO added.
  - Validation for vehicle registration number.
- API to get resident by name. [VEHMS-M01-T0012] [VEHMS-M01-T0013]
  - Regex-based name validation.
  - Error message if resident does not exist.
- API to get all residents. [VEHMS-M01-T0010] [VEHMS-M01-T0011]
- API to create resident with vehicles. [VEHMS-M01-T007]
  - Global exception for mandatory fields.
  - Not-null validation for enums.
- Swagger documentation added. [VEHMS-M01-T008] [VEHMS-M01-T009]
  - @Operation – Describe API functionality.
  - @Parameter – Document API method parameters.
  - @Tag – Group related APIs.

### Changed
- Endpoint names standardized to follow REST conventions.
- Validation added for nested vehicle data.
- Format validations added:
  - Email format validation to avoid invalid emails.
  - Flat number and mobile number format validation.

### Fixed
- Bugs found during application testing resolved. [VEHMS-M01-T0014]

### Testing
- Full application tested and pushed.  [VEHMS-M01-T0016] [VEHMS-M01-T0018]

### Infrastructure
- Tables created with one-to-many mapping between Resident and Vehicle. [VEHMS-M01-T006]
- Entities implemented: Resident and Vehicle. [VEHMS-M01-T005]
- Spring Boot configured for PostgreSQL: datasource, JDBC URL, username/password, dialect. [VEHMS-M01-T003]
- PostgreSQL local setup verified via pgAdmin; `ridematrix_vms_db` database created. [VEHMS-M01-T002]
- RideMatrix Spring Boot project initialized. [VEHMS-M01-T001]
