package com.coding.meet.newsapp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.domain.model.Source
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.dark_mode
import news_kmp_app.shared.generated.resources.light_mode
import news_kmp_app.shared.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource

const val BASE_URL = "https://newsapi.org/v2/"
const val DB_Name = "NewsDB"
const val dataStoreFileName = "setting.preferences_pb"

val categoryList = arrayListOf(
    "Business",
    "Entertainment",
    "General",
    "Health",
    "Science",
    "Sports",
    "Technology",
)

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode),
}


val previewArticles: List<Article> = listOf(
    Article(
        source = Source("techcrunch", "TechCrunch"),
        author = "Alex Wilhelm",
        title = "SpaceX Starship Completes Historic Orbital Flight Test and Controlled Splashdown",
        description = "SpaceX successfully launched its massive Starship rocket on its most ambitious test flight to date, reaching orbital velocity before executing a precision reentry.",
        url = "https://techcrunch.com",
        urlToImage = "https://images.unsplash.com/photo-1517976487502-5f79a955702f?w=800&auto=format&fit=crop&q=80",
        publishedAt = "2026-10-26T08:30:00Z",
        content = "Starship lifted off from the Starbase facility in Boca Chica, Texas. All 33 Raptor engines on the Super Heavy booster fired in unison, propelling the vehicle into the upper atmosphere.",
    ),
    Article(
        source = Source("wired", "Wired"),
        author = "Steven Levy",
        title = "Next-Gen AI Models Demonstrate Real-Time Multimodal Reasoning Across Science and Code",
        description = "Researchers have unveiled new foundation models capable of complex scientific deduction, high-speed code generation, and low-latency voice interaction.",
        url = "https://wired.com",
        urlToImage = "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=800&auto=format&fit=crop&q=80",
        publishedAt = "2026-10-26T07:15:00Z",
        content = "The breakthrough lies in unified multimodal training, allowing the model to analyze diagrams, live video feeds, and codebases simultaneously without losing context.",
    ),
    Article(
        source = Source("reuters", "Reuters"),
        author = "Paul Lienert",
        title = "Solid-State Battery Breakthrough Unlocks 1,000 KM Range for Next-Generation Electric Cars",
        description = "Automakers and battery startups announce production milestones for silicon-anode solid-state cells, promising 10-minute fast charging and longer lifespans.",
        url = "https://reuters.com",
        urlToImage = "https://images.unsplash.com/photo-1563986768609-322da13575f3?w=800&auto=format&fit=crop&q=80",
        publishedAt = "2026-10-25T19:40:00Z",
        content = "The new solid electrolyte architecture eliminates flammable liquid components while achieving double the energy density of traditional lithium-ion packs.",
    ),
    Article(
        source = Source("bloomberg", "Bloomberg"),
        author = "Matt Levine",
        title = "Global Tech Markets Rally as Central Banks Signal Balanced Long-Term Economic Outlook",
        description = "Equities touched record highs amid cooling inflation figures and strong quarterly earnings across the semiconductor and software sectors.",
        url = "https://bloomberg.com",
        urlToImage = "https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800&auto=format&fit=crop&q=80",
        publishedAt = "2026-10-25T14:20:00Z",
        content = "Investors welcomed the latest economic indicators, sending major indices higher led by semiconductor manufacturers and cloud infrastructure providers.",
    ),
    Article(
        source = Source("theverge", "The Verge"),
        author = "Nilay Patel",
        title = "Next-Gen 2nm Mobile Processors Achieve Unprecedented Performance and Battery Efficiency",
        description = "Silicon foundries begin mass production of 2-nanometer gate-all-around chips, bringing desktop-grade computing power to slim handheld devices.",
        url = "https://theverge.com",
        urlToImage = "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800&auto=format&fit=crop&q=80",
        publishedAt = "2026-10-25T11:05:00Z",
        content = "The shift to nanosheet transistors delivers a 15 percent speed boost at the same power level, or a 30 percent power reduction at identical performance.",
    ),
)

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90))

val FadeOut = fadeOut(animationSpec = tween(90))
