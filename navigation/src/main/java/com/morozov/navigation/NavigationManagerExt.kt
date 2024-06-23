package com.morozov.navigation


fun NavigationManager.goBack() {
    send(NavigationAction.GoBack)
}

fun NavigationManager.goToBooksDetail(bookDetail: BookDetail) {
    goTo(bookDetail)
}

fun NavigationManager.goToAboutMe(aboutMe: AboutMe) {
    goTo(aboutMe)
}

fun NavigationManager.goTo(direction: NavigationObject) {
    send(NavigationAction.GoTo(direction = direction))
}
