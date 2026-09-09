package com.aiphotostudio.app.domain

enum class EnhancementMode(
    val title: String,
    val description: String,
    val icon: String
) {
    PROFESSIONAL("Professional", "Polished portraits and profile photos", "PRO"),
    SOCIAL_MEDIA("Social Media", "Stand out in every scroll", "SOC"),
    ID_PHOTO("ID Photo", "Clean, compliant and ready to use", "ID")
}