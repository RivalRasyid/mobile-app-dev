package com.example.facedx.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.facedx.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullText = """
            Tentang FaceDX
            FaceDX adalah aplikasi inovatif yang dirancang untuk membantu pengguna mengatasi kesulitan dalam mendiagnosis kondisi kulit wajah mereka sendiri dan memahami langkah-langkah perawatan yang tepat. Aplikasi ini hadir untuk menjawab kebutuhan masyarakat yang sering kali bingung memulai perawatan kulit dari mana dan bagaimana cara merawatnya dengan benar. Sebelumnya, FaceDX telah tersedia dalam versi mobile app, namun versi tersebut masih dalam tahap pengembangan awal dan belum sepenuhnya optimal. Oleh karena itu, tim kami terus berupaya menyempurnakan aplikasi ini agar dapat memberikan pengalaman pengguna yang lebih baik dan hasil yang lebih akurat.

            Tim Pengembang FaceDX
            FaceDX dikembangkan oleh tim yang berdedikasi dan bersemangat, terdiri dari:
            • Product Owner: Barzy, yang bertanggung jawab atas visi produk, strategi pengembangan, dan memastikan aplikasi sesuai dengan kebutuhan pengguna.
            • Scrum Master: Rival, yang memimpin koordinasi tim, memastikan kelancaran proses pengembangan, dan menjaga semangat kolaborasi dalam tim.
            • Team Developer: Anton dan Azhar, duo pengembang yang bekerja keras untuk mewujudkan ide menjadi kode yang fungsional dan andal.
            Kolaborasi tim ini menjadi kunci utama dalam menghadirkan FaceDX sebagai solusi teknologi untuk perawatan kulit yang praktis dan mudah diakses.

            Tujuan Pengembangan FaceDX
            FaceDX lahir dari sebuah permasalahan nyata yang dihadapi banyak orang: sulitnya mendiagnosis kondisi kulit wajah secara mandiri dan kurangnya pengetahuan tentang langkah perawatan yang tepat. Banyak individu merasa kebingungan dalam memilih produk perawatan kulit atau menentukan rutinitas perawatan yang sesuai dengan jenis kulit mereka. FaceDX hadir untuk memberikan solusi dengan memanfaatkan teknologi untuk menganalisis kondisi kulit dan memberikan rekomendasi perawatan yang dipersonalisasi. Dengan aplikasi ini, pengguna dapat memulai perjalanan perawatan kulit mereka dengan lebih percaya diri, tanpa perlu kebingungan atau merasa kewalahan.

            Proses Pengembangan dan Tantangan
            Dalam perjalanan pengembangan FaceDX, tim kami menghadapi sejumlah tantangan yang menguji ketangguhan dan kreativitas kami:
            1. Keterbatasan Pengetahuan Teknis
            Anggota tim masih dalam tahap belajar dan belum memiliki keahlian tingkat lanjut di bidang pengembangan aplikasi berbasis kecerdasan buatan. Oleh karena itu, proses pengembangan FaceDX dilakukan sambil terus mempelajari teknologi baru, seperti pengolahan gambar untuk analisis kulit dan algoritma machine learning. Meskipun tantangan ini cukup signifikan, semangat untuk terus belajar memungkinkan tim untuk menghasilkan produk yang terus berkembang.

            2. Keterbatasan Waktu
            Proyek FaceDX memiliki batas waktu pengembangan yang sangat ketat, yaitu hanya 25 hari. Dalam waktu singkat ini, tim harus bekerja secara intensif untuk menyelesaikan berbagai tugas pengembangan, mulai dari desain antarmuka hingga pengujian fitur. Setiap sprint ditargetkan untuk menyelesaikan lebih dari lima tugas, yang menuntut efisiensi, komunikasi yang baik, dan manajemen waktu yang ketat. Meski penuh tekanan, keterbatasan ini mendorong tim untuk lebih kreatif dan produktif.

            3. Kualitas Dataset
            Salah satu tantangan terbesar adalah keterbatasan dataset yang digunakan untuk melatih model analisis kulit FaceDX. Dataset yang kami miliki belum sepenuhnya optimal karena tidak sepenuhnya bersumber dari riset langsung oleh dokter kulit atau ahli kecantikan. Hal ini memengaruhi akurasi prediksi kondisi kulit yang dihasilkan oleh aplikasi. Untuk mengatasi hal ini, tim berencana untuk bekerja sama dengan para ahli di bidang dermatologi guna memperkaya dataset dan meningkatkan keakuratan aplikasi di masa mendatang.

            Visi ke Depan
            Meskipun menghadapi berbagai kendala, tim FaceDX tetap optimis dan berkomitmen untuk terus mengembangkan aplikasi ini. Ke depannya, kami berencana untuk:
            • Meningkatkan Akurasi Analisis: Dengan memperluas dataset dan melibatkan ahli dermatologi untuk memvalidasi data.
            • Menyempurnakan Fitur: Menambahkan fitur seperti panduan perawatan kulit harian, rekomendasi produk berdasarkan jenis kulit, dan integrasi dengan teknologi terbaru seperti augmented reality untuk analisis real-time.
            • Memperluas Aksesibilitas: Menjadikan FaceDX tersedia di lebih banyak platform dan dalam berbagai bahasa untuk menjangkau pengguna di seluruh dunia.

            Mengapa Memilih FaceDX?
            FaceDX bukan sekadar aplikasi, tetapi solusi bagi siapa saja yang ingin memahami kulit mereka dengan lebih baik dan merawatnya dengan cara yang tepat. Dengan antarmuka yang ramah pengguna dan rekomendasi yang dipersonalisasi, FaceDX membantu mengatasi kebingungan dalam perawatan kulit, menjadikannya lebih mudah, terjangkau, dan efektif. Meskipun masih dalam tahap pengembangan, FaceDX adalah langkah awal menuju revolusi perawatan kulit berbasis teknologi yang inklusif dan inovatif.

            Ayo Bergabung dengan Perjalanan Kami!
            Kami mengundang pengguna untuk mencoba FaceDX dan memberikan masukan berharga untuk membantu kami menyempurnakan aplikasi ini. Bersama-sama, kita dapat menciptakan solusi perawatan kulit yang lebih cerdas dan terjangkau untuk semua orang. Unduh FaceDX sekarang dan mulailah perjalanan perawatan kulit Anda dengan langkah yang tepat!
        """.trimIndent()

        binding.aboutContent.text = fullText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
