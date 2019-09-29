package net.bhl.matsim.uam.modechoice;

import ch.ethz.matsim.mode_choice.framework.ModeChoiceTrip;
import ch.ethz.matsim.mode_choice.mode_availability.DefaultModeAvailability;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.population.PersonUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to filter trips according to the person attribute of
 * "having license" for the car mode.
 *
 * @author balacmi (Milos Balac), RRothfeld (Raoul Rothfeld)
 */
public class LicenseModeAvailability extends DefaultModeAvailability {
	final private String CAR_MODE = "car";

	public LicenseModeAvailability(Collection<String> modes) {
		super(modes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<String> getAvailableModes(List<ModeChoiceTrip> trips) {
		if (trips.size() > 0) {
			Person person = trips.get(0).getPerson();

			if (!PersonUtils.hasLicense(person)) {
				return super.getAvailableModes(trips).stream().filter(m -> !CAR_MODE.equals(m))
						.collect(Collectors.toSet());
			}
		}

		return super.getAvailableModes(trips);
	}

}
