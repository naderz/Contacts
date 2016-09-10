package nz.co.roobics.contacts.contacts;

import dagger.Component;
import nz.co.roobics.contacts.contacts.services.ContactsApiModule;
import nz.co.roobics.contacts.di.FragmentScoped;
import nz.co.roobics.contacts.di.NetComponent;

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = {ContactsPresenterModule.class, ContactsApiModule.class})
public interface ContactsComponent {

    void inject(ContactsFragment fragment);
}