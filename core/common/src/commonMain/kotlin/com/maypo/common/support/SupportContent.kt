package com.maypo.common.support

data class SupportContact(
    val centerName: String,
    val phoneDisplay: String,
    val phoneUri: String,
)

data class SupportFaq(
    val id: String,
    val question: String,
    val answer: String,
)

data class SupportContent(
    val contact: SupportContact,
    val faqs: List<SupportFaq>,
)
