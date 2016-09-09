package nz.co.roobics.contacts.contacts;

import dagger.Component;
import nz.co.roobics.contacts.di.FragmentScoped;
import nz.co.roobics.contacts.di.NetComponent;

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = ContactsPresenterModule.class)
public interface ContactsComponent {

    void inject(MainActivity activity);
}