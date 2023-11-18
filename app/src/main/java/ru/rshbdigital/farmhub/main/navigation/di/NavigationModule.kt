package ru.rshbdigital.farmhub.main.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.rshbdigital.farmhub.main.navigation.RouteNavigator
import ru.rshbdigital.farmhub.main.navigation.RouteNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {
    @Binds
    abstract fun bindRouteNavigator(
        routeNavigatorImpl: RouteNavigatorImpl,
    ): RouteNavigator
}