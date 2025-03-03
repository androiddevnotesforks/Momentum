package com.mwaibanda.momentum.di

import com.mwaibanda.momentum.domain.usecase.cache.GetAllItemsUseCase
import com.mwaibanda.momentum.domain.usecase.cache.GetItemUseCase
import com.mwaibanda.momentum.domain.usecase.cache.InvalidateItemsUseCase
import com.mwaibanda.momentum.domain.usecase.cache.SetItemUseCase
import com.mwaibanda.momentum.domain.usecase.event.GetEventsUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.GetBooleanUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.GetIntUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.GetStringUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.SetBooleanUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.SetIntUseCase
import com.mwaibanda.momentum.domain.usecase.localDefaults.SetStringUseCase
import com.mwaibanda.momentum.domain.usecase.meal.GetMealUseCase
import com.mwaibanda.momentum.domain.usecase.meal.PostMealUseCase
import com.mwaibanda.momentum.domain.usecase.meal.PostVolunteeredMealUseCase
import com.mwaibanda.momentum.domain.usecase.message.DeleteNoteUseCase
import com.mwaibanda.momentum.domain.usecase.message.GetAllMessagesUseCase
import com.mwaibanda.momentum.domain.usecase.message.MessageUseCases
import com.mwaibanda.momentum.domain.usecase.message.PostNoteUseCase
import com.mwaibanda.momentum.domain.usecase.message.UpdateNoteUseCase
import com.mwaibanda.momentum.domain.usecase.notification.PostNotificationUseCase
import com.mwaibanda.momentum.domain.usecase.payment.CheckoutUseCase
import com.mwaibanda.momentum.domain.usecase.sermon.GetSermonsUseCase
import com.mwaibanda.momentum.domain.usecase.service.GetAllServicesUseCase
import com.mwaibanda.momentum.domain.usecase.service.GetServiceByTypeUseCase
import com.mwaibanda.momentum.domain.usecase.service.PostVolunteerServiceDayUseCase
import com.mwaibanda.momentum.domain.usecase.service.PostVolunteerServiceUseCase
import com.mwaibanda.momentum.domain.usecase.service.ServicesUseCases
import com.mwaibanda.momentum.domain.usecase.transaction.GetTransactionsUseCase
import com.mwaibanda.momentum.domain.usecase.transaction.PostTransactionUseCase
import com.mwaibanda.momentum.domain.usecase.user.DeleteRemoteUserUseCase
import com.mwaibanda.momentum.domain.usecase.user.GetUserUseCase
import com.mwaibanda.momentum.domain.usecase.user.PostUserUseCase
import com.mwaibanda.momentum.domain.usecase.user.UpdateUserCase
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
    single { UpdateUserCase(userRepository = get()) }
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
    single { InvalidateItemsUseCase(cacheRepository = get()) }
    /**
     * @Meal - Use-cases
     */
    single { GetMealUseCase(mealRepository = get()) }
    single { PostMealUseCase(mealRepository = get()) }
    single { PostVolunteeredMealUseCase(mealRepository = get()) }
    /**
     * @Message - Use-cases
     */
    single { GetAllMessagesUseCase(messageRepository = get()) }
    single { PostNoteUseCase(messageRepository = get()) }
    single { UpdateNoteUseCase(messageRepository = get()) }
    single { DeleteNoteUseCase(messageRepository = get()) }
    single { MessageUseCases(create = get(), read = get(), update = get(), delete = get(), clearCache = get()) }
    /**
     * @Services - Use-cases
     */
    single { GetAllServicesUseCase(serviceRepository = get()) }
    single { GetServiceByTypeUseCase(serviceRepository = get()) }
    single { PostVolunteerServiceUseCase(serviceRepository = get()) }
    single { PostVolunteerServiceDayUseCase(serviceRepository = get()) }
    single { ServicesUseCases(create = get(), readByType = get(), read = get(), updateDay = get()) }
    /**
     * @Notification - Use-cases
     */
    single { PostNotificationUseCase(notificationRepository = get()) }
    /**
     * @Event - Use-cases
     */
    single { GetEventsUseCase(eventRepository = get()) }
}

