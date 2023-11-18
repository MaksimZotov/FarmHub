package ru.rshbdigital.farmhub.core.navigation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.rshbdigital.farmhub.core.navigation.RouteNavigator
import ru.rshbdigital.farmhub.core.navigation.RouteNavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindRouteNavigator(
        routeNavigatorImpl: RouteNavigatorImpl,
    ): RouteNavigator
}