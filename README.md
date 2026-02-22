# TUTORIAL MODUL 1 - CODING STANDARDS

```
Nama    : Nisrina Alya Nabilah
NPM     : 2406425924
Kelas   : Pemrograman Lanjut B
```

---

## Reflection 1
### Clean Code Principles & Secure Coding Practices
Dalam pengerjaan fitur *Create, Find, Edit,* dan *Delete* product menggunakan Spring Boot, saya telah menerapkan beberapa prinsip **Clean Code** dan **Secure Coding** sebagai berikut:

1.  **Meaningful Names**: Saya menggunakan nama variabel dan metode yang deskriptif. Contohnya, menggunakan `productId` dan `productName` daripada hanya `id` atau `n`, sehingga code mudah dipahami tanpa perlu komentar tambahan untuk menjelaskan variabel tersebut
2.  **Small Functions**: Setiap method di dalam `ProductController` dan `ProductServiceImpl` difokuskan untuk melakukan satu hal saja (*Single Responsibility Principle*), seperti hanya menangani logika penghapusan atau pembaruan data
3.  **Command Query Separation**: Metode seperti `create()` pada service melakukan perubahan state (Command), sedangkan `findAll()` hanya mengembalikan data (Query), tidak melakukan keduanya sekaligus
4.  **Secure Coding - Input Validation**: Meskipun masih sederhana, penggunaan model objek `Product` membantu dalam memetakan data input secara eksplisit, mengurangi risiko manipulasi data mentah yang tidak terstruktur
5.  **Boy Scout Rule**: Saya telah memastikan tidak ada dead code atau komentar yang tidak perlu (seperti potongan code yang di-comment out) tetap tinggal di dalam repositori.

**Improvement Plan:**
Kedepannya, saya perlu meningkatkan aspek **Error Handling** dengan menggunakan blok `try-catch` yang lebih spesifik atau menggunakan `@ControllerAdvice` untuk menangani kasus ketika produk yang ingin diedit atau dihapus tidak ditemukan (ID tidak valid), agar aplikasi lebih robust dan aman untuk project ini

---

## Reflection 2

### 1. Unit Testing & Code Coverage
Setelah menulis Unit Test, saya merasa lebih percaya diri saat melakukan perubahan code (refactoring) karena adanya secure net dari unit test ini
    - Berapa banyak Unit Test dalam satu kelas?
        - Tidak ada jumlah pasti, namun setiap alur logika (termasuk positive dan negative scenario) harus di-cover oleh unit-test
    - Apakah 100% Code Coverage menjamin bebas bug?
        - Tidak selalu. Code coverage hanya menunjukkan baris code mana yang dieksekusi saat test berjalan, namun tidak menjamin bahwa logika baik dari segi logika bisnis atua logika teknis nya sudah benar secara kontekstual atau mampu menangani semua kemungkinan corner cases, sehingga, bug logika masih sangat mungkin terjadi meskipun semua baris sudah 'passed' oleh test.

### 2. Evaluasi Kebersihan Code pada Functional Test Suite
Jika saya membuat kelas baru `CreateProductFunctionalTest.java` dengan prosedur setup yang identik dengan kelas sebelumnya (misal: `HomePageFunctionalTest`), muncul masalah kebersihan code yaitu Code Duplication
* Masalah yang diidentifikasi:
    * Redundansi Setup: Inisialisasi `WebDriver`, pengaturan port, dan penutupan driver yang berulang di setiap kelas test.
* Maintainability: 
    * Jika di masa depan ada perubahan (misalnya ganti dari `ChromeDriver` ke `FirefoxDriver`), saya harus mengubahnya di banyak tempat.

* Saran Perbaikan:
  * Base Class Implementation: Membuat sebuah base class (misalnya `BaseFunctionalTest`) yang berisi semua konfigurasi `@SpringBootTest`, setup driver, dan pembersihan driver. Kelas test fungsional lainnya cukup melakukan `extends` ke base class tersebut. 
  * Page for Object Model: Menggunakan pola page object model untuk memisahkan logika interaksi elemen UI dengan logika verifikasi test, sehingga code lebih mudah dibaca.

---

# TUTORIAL MODUL 2 - CI/CD & DevOps

---

## Refleksi Tutorial 2

1. Daftar masalah kualitas kode yang diperbaiki dan strategi memperbaikinya
    Selama pengerjaan tutorial dan latihan ini, beberapa masalah kualitas kode yang saya temukan dan perbaiki adalah:
    - Keamanan Izin Token (Token Permissions): Scorecard Bot mendeteksi bahwa izin ``GITHUB_TOKEN`` terlalu luas, yang ditunjukkan dengan skor 0, Strategi perbaikannya adalah dengan menambahkan blok permissions: `read-all` atau membatasi izin secara spesifik menjadi `contents: read` pada file workflow YAML agar sistem hanya memiliki hak akses minimum yang diperlukan.
    - Ketidakcocokan Versi Gradle (Gradle Compatibility): Terjadi error `getConvention()` karena penggunaan Gradle 9 yang tidak kompatibel dengan beberapa plugin lama, strategi perbaikannya adalah melakukan downgrade versi Gradle ke 8.10 menggunakan perintah ./gradlew wrapper agar sesuai dengan spesifikasi plugin SonarQube dan Spring Boot.
    - Security Hotspot: `SonarCloud` mendeteksi ketiadaan verifikasi integritas pada library yang diunduh, strategi perbaikannya adalah menjalankan perintah `--write-verification-metadata` untuk membuat file `verification-metadata.xml` guna memastikan setiap library yang digunakan telah melalui proses pengecekan `checksum`.
   

2. Apakah implementasi saat ini sudah memenuhi definisi Continuous Integration (CI) dan Continuous Deployment (CD)?
   - Menurut saya, implementasi saat ini sudah memenuhi kriteria CI/CD, hal ini dikarenakan setiap kali ada perubahan kode yang dikirim (push) ke repositori, GitHub Actions secara otomatis menjalankan rangkaian unit test dan analisis kualitas kode (CI) untuk memastikan tidak ada fitur yang rusak, selain itu, proses code delivery ke Platform as a Service (PaaS) seperti Koyeb juga sudah otomatis terpicu begitu kode digabungkan ke branch main, sehingga aplikasi versi terbaru dapat langsung diakses oleh pengguna tanpa intervensi manual (CD).

---
