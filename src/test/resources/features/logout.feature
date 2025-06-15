Feature: Pengguna Log-Out dari Sistem

  Sebagai pengguna yang telah berhasil login,
  Saya ingin dapat keluar dari sistem
  Sehingga akun saya aman setelah saya selesai menggunakan aplikasi.

  Background:
    Given the user is on the developer login page
    When the user enters email "adminDwi@ugm.ac.id" and password "AdminDwi"
    And the user clicks the login button
    Then the system doing authentification and user redirected to the dashboard

  Scenario: Pengguna melakukan log-out dan diarahkan kembali ke halaman login
    When the user clicks the logout button
    Then the user should be redirected to the login page
