package com.example.facedx.database

import com.example.facedx.R

enum class SkinType(
    val title: String,
    val imageRes: Int,
    val descRes: Int,
    val ringkasanRes: Int,
    val perawatanRes: Int
) {
    SEHAT(
        "Kulit Sehat",
        R.drawable.kulit_sehat,
        R.string.desc_kulit_normal,
        R.string.ringkasan_kulit_normal,
        R.string.perawatan_kulit_normal
    ),
    BERMINYAK(
        "Kulit Berminyak",
        R.drawable.kulit_minyak,
        R.string.desc_kulit_berminyak,
        R.string.ringkasan_kulit_berminyak,
        R.string.perawatan_kulit_berminyak
    ),
    KERING(
        "Kulit Kering",
        R.drawable.kulit_kering,
        R.string.desc_kulit_kering,
        R.string.ringkasan_kulit_kering,
        R.string.perawatan_kulit_kering
    );
}
