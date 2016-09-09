package nz.co.roobics.contacts.contacts.details;

import dagger.Component;
import nz.co.roobics.contacts.contacts.ContactsPresenterModule;
import nz.co.roobics.contacts.contacts.MainActivity;
import nz.co.roobics.contacts.di.FragmentScoped;
import nz.co.roobics.contacts.di.NetComponent;

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = DetailsPresenterModule.class)
public interface DetailsComponent {

    void inject(DetailsActivity activity);
}