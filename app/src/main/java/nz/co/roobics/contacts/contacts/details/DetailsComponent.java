package nz.co.roobics.contacts.contacts.details;

import dagger.Component;
import nz.co.roobics.contacts.contacts.MainActivity;
import nz.co.roobics.contacts.di.FragmentScoped;

@FragmentScoped
@Component(modules = DetailsPresenterModule.class)
public interface DetailsComponent {

    void inject(DetailsActivity activity);

}