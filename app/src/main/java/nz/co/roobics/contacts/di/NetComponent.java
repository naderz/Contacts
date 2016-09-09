package nz.co.roobics.contacts.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetModule.class
        }
)
public interface NetComponent {

    //ContactsComponent plus(ContactsModule contactsModule);

}