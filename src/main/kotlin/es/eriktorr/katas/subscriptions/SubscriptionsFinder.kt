package es.eriktorr.katas.subscriptions

interface SubscriptionsFinder {

    fun subscriptionsOf(userName: String): List<String>

}