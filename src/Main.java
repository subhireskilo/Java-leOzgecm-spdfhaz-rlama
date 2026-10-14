import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        JobExperience experience1 = new JobExperience(
                "Junior Yazilim Gelistirici",
                "Mavi Teknoloji",
                "06/2021 - 08/2022",
                "Java ile uygulama gelistirdim. "
                        + "SQL sorgulari yazdim ve hata ayiklama "
                        + "calismalarina destek verdim."
        );

        JobExperience experience2 = new JobExperience(
                "Yazilim Gelistirici",
                "Kuzey Yazilim",
                "09/2022 - 12/2024",
                "Kurumsal uygulamalarin gelistirilmesinde "
                        + "gorev aldim. REST API, veritabani "
                        + "islemleri ve birim testleri uzerinde calistim."
        );

        JobExperience experience3 = new JobExperience(
                "Kidemli Yazilim Gelistirici",
                "Pusula Bilisim",
                "01/2025 - Gunumuz",
                "Yazilim projelerinin tasarimina katkida bulundum. "
                        + "Kod incelemeleri yaptim ve ekip uyelerinin "
                        + "teknik sorunlarinin cozulmesine destek oldum."
        );

        List<JobExperience> experiences = Arrays.asList(
                experience1,
                experience2,
                experience3
        );

        Resume resume = new Resume(
                "Ahmet Yilmaz",
                "Yazilim Muhendisi",
                "ahmet.yilmaz@example.com",
                "+90 555 111 22 33",
                "Istanbul, Turkiye",
                "Java ve nesne yonelimli programlama alanlarinda "
                        + "calisan bir yazilim muhendisiyim. "
                        + "Uygulama gelistirme, veritabani ve "
                        + "yazilim testleri konusunda deneyimliyim.",
                "Bilgisayar Muhendisligi - Ornek Universitesi "
                        + "(2017-2021)",
                "Java, OOP, SQL, Maven, Git, REST API, Apache PDFBox",
                "Turkce (ana dil), Ingilizce (B2)",
                experiences
        );

        PdfGenerator generator = new PdfGenerator();

        try {
            String outputPath = "Ozgecmis.pdf";
            String photopath = "src/resources/profile.jpg";

            generator.generateResume(
                    resume,
                    outputPath,
                    photopath
            );

            System.out.println(
                    "PDF basariyla olusturuldu: "
                            + new File(outputPath).getAbsolutePath()
            );

        } catch (IOException e) {
            System.err.println(
                    "PDF olusturulurken hata: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}