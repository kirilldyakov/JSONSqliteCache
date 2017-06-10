package ru.strongit.jsonsqlitecache;

import android.app.Application;

import com.facebook.stetho.Stetho;

import ru.strongit.jsonsqlitecache.DAO.HelperFactory;

/**
 * Created by user on 10.06.17.
 */

public class EPGApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
        stetho_init();
    }

    private void stetho_init() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder;
        initializerBuilder = Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}