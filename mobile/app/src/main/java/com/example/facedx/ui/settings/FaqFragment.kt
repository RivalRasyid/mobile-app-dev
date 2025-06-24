package com.example.facedx.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facedx.databinding.FragmentFaqBinding


class FaqFragment : Fragment() {

    private lateinit var binding: FragmentFaqBinding
    private lateinit var faqAdapter: FaqAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val faqList = listOf(
            FaqItem("Apa itu FaceDX?", "FaceDX adalah aplikasi inovatif yang membantu pengguna mendiagnosis kondisi kulit wajah secara mandiri dan memberikan rekomendasi perawatan kulit yang dipersonalisasi. Dengan teknologi analisis gambar, FaceDX mengidentifikasi masalah kulit seperti jerawat, kulit kering, atau komedo, serta menyarankan rutinitas perawatan yang sesuai dengan kebutuhan pengguna.\n"),
            FaqItem("Bagaimana cara kerja FaceDX?", "FaceDX memungkinkan pengguna untuk mengunggah foto wajah melalui kamera ponsel. Aplikasi ini kemudian menggunakan algoritma kecerdasan buatan untuk menganalisis kondisi kulit, seperti tingkat kelembapan, tekstur, atau masalah spesifik. Setelah analisis selesai, FaceDX menyediakan laporan singkat tentang kondisi kulit Anda beserta rekomendasi perawatan, termasuk saran produk atau langkah-langkah perawatan harian.\n"),
            FaqItem("Siapa yang mengembangkan FaceDX?", "FaceDX dikembangkan oleh tim berdedikasi yang terdiri dari Barzy (Product Owner), Rival (Scrum Master), serta Anton dan Azhar (Team Developer). Tim ini bekerja sama untuk menciptakan solusi teknologi yang memudahkan perawatan kulit bagi semua orang.\n"),
            FaqItem("Apakah FaceDX sudah tersedia untuk umum?", "Ya, FaceDX tersedia dalam versi mobile app yang dapat diunduh melalui toko aplikasi resmi. Meskipun masih dalam tahap pengembangan, aplikasi ini sudah dapat digunakan untuk analisis kulit dasar. Kami terus memperbarui fitur untuk meningkatkan akurasi dan pengalaman pengguna. Silakan unduh dan berikan masukan untuk membantu kami menyempurnakan FaceDX!\n"),
            FaqItem("Apakah FaceDX gratis untuk digunakan?", "FaceDX saat ini gratis untuk fitur-fitur dasar, seperti analisis kulit dan rekomendasi perawatan. Kami mungkin akan memperkenalkan fitur premium berbayar di masa depan, tetapi fitur inti akan tetap dapat diakses secara gratis untuk memastikan semua pengguna dapat menikmati manfaat FaceDX.\n"),
            FaqItem("Seberapa akurat hasil diagnosis dari FaceDX?", "FaceDX menggunakan algoritma kecerdasan buatan untuk menganalisis kondisi kulit berdasarkan dataset yang kami kembangkan. Meskipun hasilnya cukup membantu untuk kebutuhan sehari-hari, akurasi mungkin belum sempurna karena dataset kami masih dalam tahap penyempurnaan. Untuk kondisi kulit yang serius atau memerlukan perhatian khusus, kami sangat menyarankan konsultasi langsung dengan dokter kulit atau ahli kecantikan.\n"),
            FaqItem("Apakah FaceDX aman untuk data pribadi saya?", "Keamanan data pengguna adalah prioritas utama kami. Foto yang diunggah hanya digunakan untuk analisis kulit dan tidak akan dibagikan kepada pihak ketiga tanpa persetujuan Anda. Kami menerapkan standar keamanan data untuk melindungi informasi pribadi Anda. Untuk informasi lebih lanjut, silakan baca kebijakan privasi kami di aplikasi.\n"),
            FaqItem("Apakah FaceDX dapat digunakan untuk semua jenis kulit?", "Ya, FaceDX dirancang untuk mendukung berbagai jenis kulit, termasuk kulit kering, berminyak, kombinasi, sensitif, atau normal. Aplikasi ini akan menganalisis kondisi kulit Anda secara spesifik dan memberikan rekomendasi yang sesuai dengan kebutuhan kulit Anda.\n"),
            FaqItem("Saya tidak dapat mengunggah foto. Apa yang harus saya lakukan?", "Jika Anda mengalami masalah saat mengunggah foto, silakan coba langkah berikut:  \n" +
                    "\n" +
                    "Pastikan foto diambil dengan pencahayaan yang baik dan wajah terlihat jelas.  \n" +
                    "Ambil ulang foto menggunakan kamera ponsel Anda.  \n" +
                    "Jika masalah berlanjut, gunakan fitur kamera langsung di aplikasi FaceDX untuk mengambil foto.Jika Anda masih menghadapi kendala, silakan hubungi tim dukungan kami di nomor 0812-3878-5850 atau melalui email yang tersedia di aplikasi. Kami siap membantu Anda!\n"),
            FaqItem("Bagaimana cara mendapatkan hasil analisis kulit yang lebih akurat?", "Untuk hasil analisis yang optimal, ikuti panduan ini:  \n" +
                    "\n" +
                    "Ambil foto di tempat dengan pencahayaan alami atau terang, hindari bayangan atau cahaya yang terlalu redup.  \n" +
                    "Pastikan wajah bersih dari riasan atau kotoran agar analisis lebih akurat.  \n" +
                    "Posisikan wajah Anda di tengah bingkai foto dan pastikan seluruh wajah terlihat jelas.  \n" +
                    "Perbarui aplikasi ke versi terbaru untuk memastikan Anda menggunakan teknologi analisis terkini.\n"),
            FaqItem("Apa yang harus saya lakukan jika aplikasi tidak memberikan rekomendasi yang sesuai?", "Jika rekomendasi yang diberikan tidak sesuai, coba lakukan analisis ulang dengan memastikan foto yang diunggah memenuhi syarat (pencahayaan baik, wajah bersih, dll.). Anda juga dapat memberikan masukan melalui fitur umpan balik di aplikasi agar kami dapat meningkatkan akurasi. Jika masalah berlanjut, hubungi tim dukungan kami di 0812-3878-5850 untuk bantuan lebih lanjut.\n"),
            FaqItem("Apakah FaceDX akan tersedia dalam bahasa lain?", "Saat ini, FaceDX tersedia dalam bahasa Indonesia dan Inggris. Kami berencana untuk menambahkan dukungan untuk bahasa lain di masa depan agar aplikasi ini dapat digunakan oleh lebih banyak pengguna di seluruh dunia. Pantau pembaruan aplikasi untuk informasi lebih lanjut!\n"),
            FaqItem("Bagaimana cara memberikan masukan atau melaporkan bug?", "Kami sangat menghargai masukan dari pengguna! Anda dapat mengirimkan masukan atau melaporkan bug melalui fitur \"Umpan Balik\" di aplikasi FaceDX. Alternatifnya, Anda dapat menghubungi kami melalui email yang tercantum di aplikasi atau melalui nomor 0812-3878-5850. Masukan Anda akan membantu kami menyempurnakan aplikasi untuk pengalaman pengguna yang lebih baik.\n"),
            FaqItem("Apakah FaceDX akan menambahkan fitur baru di masa depan?", "Ya, kami sedang bekerja untuk menambahkan fitur baru, seperti panduan perawatan kulit harian yang lebih terperinci, rekomendasi produk berdasarkan merek lokal, dan kemungkinan integrasi dengan teknologi augmented reality untuk analisis real-time. Ikuti pembaruan aplikasi kami untuk mencoba fitur-fitur terbaru!\n" +
                    "Hubungi KamiJika Anda memiliki pertanyaan lain atau membutuhkan bantuan, jangan ragu untuk menghubungi tim dukungan FaceDX di 0812-3878-5850 atau melalui fitur dukungan di aplikasi. Kami siap membantu Anda memulai perjalanan perawatan kulit dengan FaceDX!\n"),
            FaqItem("Siapakah nama tim pembuat FaceDX?", "BareFace yang di mana memiliki arti sebagai muka barzy karena dia adalah Product owner dari project ini jadi untuk menghormatinya kami mengabadikan namanya sebagai nama tim")
        )

        faqAdapter = FaqAdapter(faqList)
        binding.recyclerViewFaq.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = faqAdapter
        }
    }
}
