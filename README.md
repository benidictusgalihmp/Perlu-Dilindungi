# IF3210-2022-Android-52

## Deskripsi aplikasi

Sebuah aplikasi mobile pada platform Android bernama Aplikasi Perlu Dilindungi yang dibangun dengan menggunakan API Perlu Dilindungi dan bahasa pemrograman Kotlin. Aplikasi yang dibangun memiliki spesifikasi sebagai berikut:

- Menampilkan Berita COVID-19
- Menampilkan Daftar Faskes untuk Vaksinasi
- Menampilkan Detail Informasi Faskes
- Menampilkan Daftar Bookmark Faskes
- Melakukan "Check-in"

## Cara kerja

1. Menampilkan Berita COVID-19

   Fitur tampilan daftar berita COVID-19 ini merupakan salah satu halaman dari aplikasi yang diimplementasi menggunakan RecyclerView yang masing-masing item berisi layout CardView, sedangkan untuk tampilan detail berita diimplementasi menggunakan WebView.

2. Menampilkan Daftar Faskes untuk Vaksinasi
3. Menampilkan Detail Informasi Faskes
4. Menampilkan Daftar Bookmark Faskes

   Fitur ini digunakan untuk melihat Faskes yang di-bookmark oleh pengguna. Daftar dari Faskes yang di-bookmark tersimpan dalam database SQLite menggunakan library room pada android.

5. Melakukan "Check-in"

   Fitur ini dapat diakses dengan menekan FloatingActionButton yang berada pada tampilan utama dan akan membuka kamera serta mengakses lokasi device, fitur dapat membaca hasil scan QR-Code melalui kamera yang kemudian mengembalikan status pengguna beserta alasannya. Selain itu, pterdapat juga fitur untuk melihat temperatur ruangan menggunakan sensor ambient temperature.

## Library yang digunakan

- Retrofit (https://square.github.io/retrofit/) : Library ini digunakan untuk berinteraksi dengan API dan mengirim API requests
- Glide (https://github.com/bumptech/glide) : Library ini digunakan untuk menampilkan gambar tajuk berita yang bersumber dari URL
- Code Scanner (https://github.com/yuriy-budiyev/code-scanner) : Library ini digunakan untuk mengimplementasikan QR-Code scanner pada fitur "Check-in"
- Room (https://developer.android.com/jetpack/androidx/releases/room) : Library ini digunakan untuk membuat abstraksi dalam program terhadap SQLite agar memudahkan pengaksesan terhadap database

## Screenshot aplikasi

Beberapa screenshot dari aplikasi Perlu Dilindungi ini dapat dilihat di [sini](https://gitlab.informatika.org/BenidictusGMP/if3210-2022-android-52/-/tree/main/Screenshots).

## Pembagian kerja anggota kelompok

13519153 :

- Menampilkan Berita COVID-19
- Menampilkan Detail Berita COVID-19 pada WebView
- Melakukan "Check-in" (Scan QR-Code)

13519154 :

- Menampilkan Daftar Bookmark Faskes
- Melakukan setup SQLite
- Melakukan "Check-in" (Mengukur suhu kamar)

13519159 :

- Menampilkan Daftar Faskes untuk Vaksinasi
- Menampilkan Detail Informasi Faskes

## Anggota kelompok

- 13519153 / Maximillian Lukman
- 13519154 / Rafi Raihansyah Munandar
- 13519159 / Benidictus Galih Mahar Putra
