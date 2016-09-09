package nz.co.roobics.contacts.contacts;

import dagger.Component;
import nz.co.roobics.contacts.contacts.details.DetailsPresenterModule;
import nz.co.roobics.contacts.contacts.services.ContactsApiModule;
import nz.co.roobics.contacts.di.FragmentScoped;
import nz.co.roobics.contacts.di.NetComponent;

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = {ContactsPresenterModule.class, DetailsPresenterModule.class, ContactsApiModule.class})
public interface ContactsDetailsComponent {

    void inject(MainActivity activity);
}