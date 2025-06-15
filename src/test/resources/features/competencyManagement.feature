# Language: en
Feature: Competency Unit Management
  As an admin,
  I want to add new competency units to the system
  So that the competency unit list is always up to date.

  Background:
    Given the user is on the developer login page
    When the user enters email "adminDwi@ugm.ac.id" and password "AdminDwi"
    And the user clicks the login button
    Then the system doing authentification and user redirected to the dashboard

  @positive
  Scenario: Successfully adding a new competency unit with valid data
    When the admin clicks the "Competency Unit Management" menu
    Then the admin should be redirected to the competency unit management page
    And the admin clicks the "Add Competency Unit" button
    And fills out the competency unit form with the following data:
      | Column                        | Value                                                                |
      | Competency Unit Code          | J.611000.005.21                                              |
      | Competency Unit Name          | Designing Code Security                                              |
      | Competency Unit Field         | BIDANG202500009                                                      |
      | Standard Type                 | SKKNI                                                                |
      | Competency Unit Elements      | Identifying threats and security needs of the network.               |
      | Competency Unit Elements      | Designing a secure network topology.                                 |
      | Competency Unit Elements      | Selecting appropriate hardware and software security solutions.      |
      | Competency Unit Elements      | Configuring security policies on network devices.                    |
      | Competency Unit Elements      | Documenting the security network design.                             |
    And the admin clicks the "Save Competency Unit" button
    Then a success notification "Unit Kompetensi berhasil ditambahkan" appears

  @negative
  Scenario: Failing to add a competency unit with a duplicate code
    When the admin clicks the "Competency Unit Management" menu
    Then the admin should be redirected to the competency unit management page
    And the admin clicks the "Add Competency Unit" button
    And fills out the competency unit form with the following data:
      | Column                        | Value                                                                 |
      | Competency Unit Code          | J.611000.005.21                                                       |
      | Competency Unit Name          | Designing Code Security                                                     |
      | Competency Unit Field         | BIDANG202500009                              |
      | Standard Type                 | SKKNI                                                                |
      | Competency Unit Elements      | Identifying threats and security needs of the network.               |
      | Competency Unit Elements      | Designing a secure network topology.                                 |
      | Competency Unit Elements      | Selecting appropriate hardware and software security solutions.      |
      | Competency Unit Elements      | Configuring security policies on network devices.                    |
      | Competency Unit Elements      | Documenting the security network design.                             |
    And the admin clicks the "Save Competency Unit" button
    Then an error message "The kode uk has already been taken." appears