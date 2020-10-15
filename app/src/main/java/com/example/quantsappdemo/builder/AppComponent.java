package com.example.quantsappdemo.builder;


import android.content.Context;


import com.example.quantsappdemo.api.API;

import dagger.Component;

@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, ApiServiceModule.class})
public interface AppComponent {

    API restAPI();

//    GeneralUtil utils();

//    EventBus eventBus();

    Context context();

//    ConnectionLiveData connectionLiveData();

//    Navigator navigator();

}
