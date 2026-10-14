# Java PDF Özgeçmiş / CV Oluşturucu (Java PDF Resume Generator)

Bu proje, Java ve Apache PDFBox kütüphanesi kullanılarak geliştirilmiş, nesne yönelimli programlama (OOP) prensiplerine uygun bir PDF formatında özgeçmiş (CV) oluşturma uygulamasıdır.

---

## 📌 Özellikler

- Dinamik Veri Yapısı: Kişisel bilgiler, eğitim, iş deneyimleri ve yetenekler nesne yönelimli mimari ile modellenmiştir.
- Görsel Entegrasyonu: src/main/resources klasöründeki profil fotoğrafını otomatik olarak PDF belgesine yerleştirir.
- Apache PDFBox Entegrasyonu: Sayfa düzeni, metin hizalama ve çizim işlemleri PDFBox API kullanılarak gerçekleştirilmiştir.
- Otomatik PDF Çıktısı: Uygulama çalıştırıldığında proje ana dizininde Ozgecmis.pdf dosyasını otomatik üretir.

---

## 🛠 Teknolojiler ve Gereksinimler

- Dil: Java 17+
- Derleme Aracı: Maven
- Kütüphane: Apache PDFBox
- Geliştirme Ortamı: IntelliJ IDEA

---

## 🚀 Kurulum ve Çalıştırma

1. Projeyi Klonlayın:
   git clone https://github.com/subhireskilo/Java-leOzgecm-spdfhaz-rlama.git

2. Görsel Kaynağını Kontrol Edin:
   src/main/resources/profile.jpg konumunda vesikalık/profil fotoğrafınızın olduğundan emin olun.

3. Uygulamayı Çalıştırın:
   Main.java dosyasını IntelliJ IDEA üzerinden çalıştırın.

4. Çıktı:
   Proje kök dizininde oluşan Ozgecmis.pdf dosyasını açıp inceleyebilirsiniz.

---

## 📁 Proje Dizin Yapısı

odevproje1/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Main.java
│       │   ├── PdfGenerator.java
│       │   ├── Resume.java
│       │   └── JobExperience.java
│       └── resources/
│           └── profile.jpg
├── pom.xml
└── README.md

---

## 👨‍💻 Geliştirici

- Öğrenci: Subhi Reşkilo
- Ders: Nesneye Yönelimli Programlama (NYP)
