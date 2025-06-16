# Language: en
Feature: Certification Schema Management
  As an admin,
  I want to add new certification schemas with detailed requirements
  So that I can manage the certification data in the system.

  Background:
    Given the user is on the developer login page
    When the user enters email "adminDwi@ugm.ac.id" and password "AdminDwi"
    And the user clicks the login button
    Then the system doing authentification and user redirected to the dashboard

  @positive
  Scenario: Successfully adding a new schema with more than one competency unit
    When the admin clicks the "Schema Management" menu
    Then the admin should be redirected to the "schema management" page
    And the admin clicks the "Add Schema" button
    And fills out the schema form with the following details:
      | Field               | Value                                                         |
      | Schema Number       | SKM-KMN-001                                                   |
      | Schema Name         | Okupasi Analis Keamanan Jaringan (Network Security Analyst)   |
      | SKKNI Document      | src/test/resources/files/skkni_dummy.pdf                      |
      | Science Field       | Teknologi Informasi dan Komunikasi                            |
      | Competency Units    | J.611000.005.02 - Merancang Keamanan Jaringan, J.611000.006.23 - Designing Code Security                 |
    And adds the following basic requirements:
      """
      1. Jurusan Teknologi Rekayasa Internet, Teknologo Rekayasa Perangkat Lunak, Sistem informasi, Ilmu Komputer, atau jurusan yang relevan.
      2. Menyerahkan fotokopi KTP/Paspor yang masih berlaku.
      3. Menyerahkan Pas Foto terbaru ukuran 3x4 (2 lembar).
      4. Menyerahkan portofolio proyek/kampanye digital yang pernah ditangani.
      5. Mengisi formulir pendaftaran APL-01 dan asesmen mandiri APL-02.
      """
    And the admin clicks the "Save Certification" button

  @positive
  Scenario: Successfully adding a new schema with one competency unit
    When the admin clicks the "Schema Management" menu
    Then the admin should be redirected to the "schema management" page
    And the admin clicks the "Add Schema" button
    And fills out the schema form with the following details:
      | Field               | Value                                                         |
      | Schema Number       | SKM-KMN-002                                                   |
      | Schema Name         | Okupasi Analis Keamanan Jaringan (Network Security Analyst)   |
      | SKKNI Document      | src/test/resources/files/skkni_dummy.pdf                      |
      | Science Field       | Teknologi Informasi dan Komunikasi                            |
      | Competency Units    | J.611000.005.02 - Merancang Keamanan Jaringan                 |
    And adds the following basic requirements:
      """
      1. Jurusan Teknologi Rekayasa Internet, Teknologo Rekayasa Perangkat Lunak, Sistem informasi, Ilmu Komputer, atau jurusan yang relevan.
      2. Menyerahkan fotokopi KTP/Paspor yang masih berlaku.
      3. Menyerahkan Pas Foto terbaru ukuran 3x4 (2 lembar).
      4. Menyerahkan portofolio proyek/kampanye digital yang pernah ditangani.
      5. Mengisi formulir pendaftaran APL-01 dan asesmen mandiri APL-02.
      """
    And the admin clicks the "Save Certification" button

  @negative
  Scenario: Failing due to competency units empty
    When the admin clicks the "Schema Management" menu
    Then the admin should be redirected to the "schema management" page
    And the admin clicks the "Add Schema" button
    And fills out the schema form with the following details:
      | Field               | Value                                                         |
      | Schema Number       | SKM-KMN-002                                                   |
      | Schema Name         | Okupasi Analis Keamanan Jaringan (Network Security Analyst)   |
      | SKKNI Document      | src/test/resources/files/skkni_dummy.pdf                      |
      | Science Field       | Teknologi Informasi dan Komunikasi                            |
      | Competency Units    |                                                               |
    And adds the following basic requirements:
      """
      1. Jurusan Teknologi Rekayasa Internet, Teknologo Rekayasa Perangkat Lunak, Sistem informasi, Ilmu Komputer, atau jurusan yang relevan.
      2. Menyerahkan fotokopi KTP/Paspor yang masih berlaku.
      3. Menyerahkan Pas Foto terbaru ukuran 3x4 (2 lembar).
      4. Menyerahkan portofolio proyek/kampanye digital yang pernah ditangani.
      5. Mengisi formulir pendaftaran APL-01 dan asesmen mandiri APL-02.
      """
    And the admin clicks the "Save Certification with Error Schema" button
    Then an error message should be shown for empty competency units


  @negative
  Scenario: Failing due to SKKNI Document empty
    When the admin clicks the "Schema Management" menu
    Then the admin should be redirected to the "schema management" page
    And the admin clicks the "Add Schema" button
    And fills out the schema form with the following details:
      | Field               | Value                                                         |
      | Schema Number       | SKM-KMN-002                                                   |
      | Schema Name         | Okupasi Analis Keamanan Jaringan (Network Security Analyst)   |
      | SKKNI Document      |                                                               |
      | Science Field       | Teknologi Informasi dan Komunikasi                            |
      | Competency Units    | J.611000.005.23 - Designing Code Security                     |
    And adds the following basic requirements:
      """
      1. Jurusan Teknologi Rekayasa Internet, Teknologo Rekayasa Perangkat Lunak, Sistem informasi, Ilmu Komputer, atau jurusan yang relevan.
      2. Menyerahkan fotokopi KTP/Paspor yang masih berlaku.
      3. Menyerahkan Pas Foto terbaru ukuran 3x4 (2 lembar).
      4. Menyerahkan portofolio proyek/kampanye digital yang pernah ditangani.
      5. Mengisi formulir pendaftaran APL-01 dan asesmen mandiri APL-02.
      """
    And the admin clicks the "Save Certification with Error Schema" button
    Then an error message should be shown for empty SKKNI document