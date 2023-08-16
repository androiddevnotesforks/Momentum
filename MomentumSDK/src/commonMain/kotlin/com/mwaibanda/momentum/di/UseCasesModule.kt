package com.mwaibanda.momentum.di

import com.mwaibanda.momentum.domain.usecase.cache.GetAllItemsUseCase
import com.mwaibanda.momentum.domain.usecase.cache.GetItemUseCase
import com.mwaibanda.momentum.domain.usecase.cache.SetItemUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.*
import com.mwaibanda.momentum.domain.usecase.meal.GetMealUseCase
import com.mwaibanda.momentum.domain.usecase.meal.PostMealUseCase
import com.mwaibanda.momentum.domain.usecase.meal.PostVolunteeredMealUseCase
import com.mwaibanda.momentum.domain.usecase.payment.CheckoutUseCase
import com.mwaibanda.momentum.domain.usecase.transaction.PostTransactionUseCase
import com.mwaibanda.momentum.domain.usecase.sermon.GetSermonsUseCase
import com.mwaibanda.momentum.domain.usecase.transaction.GetTransactionsUseCase
import com.mwaibanda.momentum.domain.usecase.user.*
import org.koin.dsl.module

val useCasesModule = module {
    /**
     * @Payment - Use-cases
     */
    single { CheckoutUseCase(paymentRepository = get()) }
    /**
     * @Transactions - Use-cases
     */
    single { PostTransactionUseCase(transactionRepository = get()) }
    single { GetTransactionsUseCase(transactionRepository = get()) }

    /**
     * @User - Use-cases
     */
    single { PostUserUseCase(userRepository = get()) }
    single { GetUserUseCase(userRepository = get()) }
    single { UpdateUserEmailUseCase(userRepository = get()) }
    single { UpdateUserFullnameUseCase(userRepository = get()) }
    single { UpdateUserPhoneUseCase(userRepository = get()) }
    single { DeleteRemoteUserUseCase(userRepository = get()) }
    /**
     * @LocalDefaults - Use-cases
     */
    single { SetStringUseCase(localDefaultsRepository = get()) }
    single { GetStringUseCase(localDefaultsRepository = get()) }
    single { SetIntUseCase(localDefaultsRepository = get()) }
    single { GetIntUseCase(localDefaultsRepository = get()) }
    single { SetBooleanUseCase(localDefaultsRepository = get()) }
    single { GetBooleanUseCase(localDefaultsRepository = get()) }
    /**
     * @Sermon - Use-cases
     */
    single { GetSermonsUseCase(sermonRepository = get()) }
    /**
     * @Cache - Use-cases
     */
    single { GetItemUseCase<Any>(cacheRepository = get()) }
    single { SetItemUseCase<Any>(cacheRepository = get()) }
    single { GetAllItemsUseCase<Any>(cacheRepository = get()) }
    /**
     * @Meal - Use-cases
     */
    single { GetMealUseCase(mealRepository = get()) }
    single { PostMealUseCase(mealRepository = get()) }
    single { PostVolunteeredMealUseCase(mealRepository = get()) }
}

