package com.coding.meet.newsapp.utils

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

/**
 * Formats an ISO-8601 date string (e.g., "2026-10-15T17:53:55Z") into a readable date & time format.
 * Example output: "Oct 15, 2026 • 5:53 PM"
 */
fun formatPublishedDate(
    isoString: String,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {
    return runCatching {
        val instant = Instant.parse(isoString)
        val ldt = instant.toLocalDateTime(timeZone)
        val month = ldt.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
        val day = ldt.day
        val year = ldt.year
        val hour = when {
            ldt.hour == 0 -> 12
            ldt.hour > 12 -> ldt.hour - 12
            else -> ldt.hour
        }
        val minute = ldt.minute.toString().padStart(2, '0')
        val amPm = if (ldt.hour >= 12) "PM" else "AM"
        "$month $day, $year • $hour:$minute $amPm"
    }.getOrDefault(isoString)
}

/**
 * Formats an ISO-8601 date string (e.g., "2026-10-15T17:53:55Z") into a relative human-friendly time.
 * Examples: "Just now", "45m ago", "3h ago", "2d ago", or "Oct 15, 2026"
 */
fun formatRelativeTime(
    isoString: String,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {
    return runCatching {
        val instant = Instant.parse(isoString)
        val now = Clock.System.now()
        val duration = now - instant

        if (duration.isNegative()) {
            return@runCatching "Just now"
        }

        val minutes = duration.inWholeMinutes
        val hours = duration.inWholeHours
        val days = duration.inWholeDays

        when {
            minutes < 1 -> "Just now"
            minutes < 60 -> "${minutes}m ago"
            hours < 24 -> "${hours}h ago"
            days < 7 -> "${days}d ago"
            else -> {
                val ldt = instant.toLocalDateTime(timeZone)
                val month = ldt.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
                "$month ${ldt.day}, ${ldt.year}"
            }
        }
    }.getOrDefault(isoString)
}

/**
 * Cleans raw article content by stripping HTML tags and NewsAPI truncation indicators.
 */
fun cleanNewsContent(raw: String): String {
    return raw
        .replace(Regex("<[^>]*>"), "")
        .replace(Regex("\\[\\+\\d+\\s+chars\\]"), "")
        .replace(Regex("(\\r?\\n){3,}"), "\n\n")
        .trim()
}

