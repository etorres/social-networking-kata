package es.eriktorr.katas.data

import es.eriktorr.katas.subscriptions.SubscriptionsFinder
import es.eriktorr.katas.subscriptions.SubscriptionsRecorder

class SubscriptionsRepository: SubscriptionsRecorder, SubscriptionsFinder {

    private val subscriptions = mutableMapOf<String, MutableList<String>>()

    override fun subscribe(userName: String, subscriptionUserName: String) {
        subscriptions.getOrPut(userName) { mutableListOf() }.add(subscriptionUserName)
    }

    override fun subscriptionsOf(userName: String): List<String> = subscriptions.getOrPut(userName) { mutableListOf() }.toList()

}