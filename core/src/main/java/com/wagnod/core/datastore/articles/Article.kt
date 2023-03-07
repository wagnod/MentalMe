package com.wagnod.core.datastore.articles

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Category(val name: String = "")

@Serializable
data class Article(
    val title: String = "",
    val link: String = "",
    val image: String = "",
    val description: String = "",
    val type: ArticleType = ArticleType.TEXT,
    val category: List<Category> = listOf()
) {
    companion object {
        fun getSampleData() = listOf(
            Article(
                title = "Meditation",
                image = "https://i0.wp.com/www.additudemag.com/wp-content/uploads/2022/04/Calming-Triggered-Emotions-Saunders_1920x1080.jpg",
                description = "The Basics Setting aside time for formal meditation is an important " +
                        "way to establish a routine and get comfortable with the practice. Even just " +
                        "a few minutes a day can make a big difference.  “Some people complain about " +
                        "taking time out of their day,” said Atman Smith, who teaches meditation to " +
                        "underserved communities in Baltimore. “Practice is important though. " +
                        "It’s a tool you can use to bring yourself back to the present in stressful " +
                        "situations.”  But we shouldn’t stop being mindful when we stop meditating. " +
                        "“The purpose of mindfulness meditation is to become mindful throughout all " +
                        "parts of our life, so that we’re awake, present and openhearted in everything " +
                        "we do,” said Tara Brach, a popular meditation teacher based near Washington, D.C." +
                        " “Not just when we’re sitting on the cushion.”  Mindfulness meditation isn’t " +
                        "about letting your thoughts wander. But it isn’t about trying to empty your mind, " +
                        "either. Instead, the practice involves paying close attention to the present moment — " +
                        "especially our own thoughts, emotions and sensations — whatever it is that’s happening.  " +
                        "In addition to basic meditation instructions, we’ve compiled guided meditations for a " +
                        "few popular exercises including the body scan, walking meditation and mindful eating. " +
                        "“Each of the applied mindfulness practices brings alive an experience that " +
                        "might otherwise be more automatic,” said Ms. Brach.  Though meditating on " +
                        "your own is an essential part of a complete practice, the steady guidance " +
                        "of an experienced teacher can be invaluable, especially as you’re getting " +
                        "started. Our minds wander so easily, and the clear instructions of a teacher " +
                        "can help bring us back to the present moment.",
                type = ArticleType.TEXT
            ),
            Article(
                title = "Self Care",
                link = "",
                image = "https://i0.wp.com/www.additudemag.com/wp-content/uploads/2022/04/Calming-Triggered-Emotions-Saunders_1920x1080.jpg",
                description = "https://en.wikipedia.org/wiki/File:Contopus-virens-001.ogg",
                type = ArticleType.AUDIO
            ),
            Article(
                title = "Health",
                link = "",
                image = "https://i0.wp.com/www.additudemag.com/wp-content/uploads/2022/04/Calming-Triggered-Emotions-Saunders_1920x1080.jpg",
                description = "Some sample text",
                type = ArticleType.TEXT
            ),
        )
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Serializable(with = ArticleType.ArticleTypeSerializer::class)
enum class ArticleType(val type: String) {
    TEXT("text"),
    AUDIO("audio"),
    UNKNOWN("");

    companion object {
        fun getType(type: String): ArticleType {
            for (value in values()) {
                if (value.type == type) {
                    return value
                }
            }
            return UNKNOWN
        }
    }

    @Serializer(forClass = ArticleType::class)
    object ArticleTypeSerializer : KSerializer<ArticleType> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor(ArticleType::class.java.name, PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: ArticleType) {
            encoder.encodeString(value.type)
        }

        override fun deserialize(decoder: Decoder): ArticleType {
            return try {
                val key = decoder.decodeString()
                getType(key)
            } catch (e: IllegalArgumentException) {
                UNKNOWN
            }
        }
    }
}