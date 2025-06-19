Proyek Automasi Testing untuk Aplikasi LSP-UGM
Deskripsi Proyek

Repositori ini berisi proyek *automated testing* yang dibangun untuk menguji fungsionalitas utama dari aplikasi web **Lembaga Sertifikasi Profesi (LSP) UGM**. Proyek ini dirancang menggunakan pendekatan **Behavior-Driven Development (BDD)** dengan Cucumber dan mengikuti design pattern **Page Object Model (POM)** untuk memastikan skrip tes yang dibuat bersih, mudah dibaca, dan mudah dipelihara.

Tujuan utama dari proyek ini adalah untuk memvalidasi alur kerja kritis pada aplikasi, memastikan setiap fitur berjalan sesuai dengan spesifikasi yang diharapkan.

## Fitur yang Diuji

Skrip automasi yang tersedia saat ini mencakup beberapa fungsionalitas utama:

-   **Autentikasi:**
    -   Login Pengguna (Skenario Positif, negatif)
    -   Logout Pengguna
-   **Manajemen Unit Kompetensi:**
    -   Menambahkan data Unit Kompetensi dengan 2 skenario positif dan 2 skenario negatif
-   **Manajemen Skema Sertifikasi:**
    -   Menambahkan data skema sertifikasi dengan 2 skenario positif dan 2 skenario negatif

## Teknologi yang Digunakan

-   **Bahasa Pemrograman:** Java (JDK 21)
-   **Build & Dependency Management:** Apache Maven
-   **Framework Automasi Browser:** Selenium WebDriver
-   **Framework BDD:** Cucumber
-   **Test Runner & Assertion:** JUnit 5
-   **Dependency Injection (DI):** PicoContainer
-   **Pelaporan (Reporting):** ExtentReports & Laporan HTML Bawaan Cucumber

## Struktur Proyek

Proyek ini mengikuti arsitektur Page Object Model (POM) untuk memisahkan logika tes dari implementasi halaman.

-   `src/main/java/pages`: Berisi semua kelas **Page Object**. Setiap kelas merepresentasikan satu halaman atau komponen di aplikasi web dan berisi locator elemen serta metode aksi.
-   `src/test/java/steps`: Berisi semua kelas **Step Definition**. Kelas-kelas ini menghubungkan langkah-langkah Gherkin di file `.feature` dengan metode aksi di Page Objects.
-   `src/test/java/runners`: Berisi kelas **Test Runner** yang mengkonfigurasi dan menjalankan tes Cucumber.
-   `src/test/resources/features`: Berisi semua file **Gherkin (`.feature`)** yang mendeskripsikan skenario tes dalam bahasa natural.
-   `test-output`: Direktori output untuk laporan dari ExtentReports.
-   `target/cucumber-reports`: Direktori output untuk laporan HTML bawaan dari Cucumber.

## Prasyarat

Sebelum menjalankan proyek ini, pastikan Anda telah menginstal:
1.  **Java Development Kit (JDK)** versi 21 atau lebih tinggi.
2.  **Apache Maven**.
3.  **Web Browser** seperti Google Chrome.

## Instalasi & Setup

1.  **Clone repositori ini:**
    ```bash
    git clone [https://github.com/nadznaf/PPPL_Proyek-PAD-LSP-UGM.git](https://github.com/nadznaf/PPPL_Proyek-PAD-LSP-UGM.git)
    ```
2.  **Buka proyek** menggunakan IDE pilihan Anda (misalnya, IntelliJ IDEA).
3.  IDE akan secara otomatis mendeteksi file `pom.xml` dan mengunduh semua dependensi yang diperlukan melalui Maven. Tunggu hingga proses ini selesai.

## Cara Menjalankan Tes

Ada beberapa cara untuk menjalankan tes otomatisasi ini.

### 1. Menjalankan Melalui IDE (Untuk Development)

Cara termudah adalah dengan menjalankan file **Test Runner** yang akan mengeksekusi semua file fitur.
-   Buka file `src/test/java/runners/testRunners.java`.
-   Klik kanan di dalam file dan pilih **Run 'testRunners'**.
-   Anda juga bisa menjalankan satu file fitur spesifik dengan membuka file `.feature` yang diinginkan dan mengklik ikon "Play" di samping `Feature: ...`.

### 2. Menjalankan Melalui Maven (Direkomendasikan untuk Otomatisasi)

Ini adalah cara standar untuk menjalankan semua tes dari command line, sangat berguna untuk CI/CD.

1.  Buka Terminal atau Command Prompt di direktori root proyek (folder yang berisi `pom.xml`).
2.  Jalankan perintah berikut untuk menjalankan **semua tes** yang ada di proyek:

    ```bash
    mvn clean test
    ```

## Melihat Laporan Hasil Tes

Setelah eksekusi selesai, Anda dapat melihat laporan visual yang detail.

-   **Laporan Cucumber:** Buka file `target/cucumber-reports/report.html` di browser Anda. Laporan ini menunjukkan status lulus/gagal dari setiap langkah Gherkin.
-   **Laporan ExtentReports:** Buka file `test-output/AutomationTestReport.html` di browser Anda untuk laporan yang lebih kaya dengan detail teknis dan log.

## Kontributor

1. Devangga Arya Harta Wijayanto  
2. Nadzira Azhani Farahiya   
3. Dwi Anggara Najwan Sugama  
4. Zhazha Nurani               
