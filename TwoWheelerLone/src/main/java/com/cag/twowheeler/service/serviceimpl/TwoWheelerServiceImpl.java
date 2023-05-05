package com.cag.twowheeler.service.serviceimpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cag.twowheeler.dto.AllVehicleOem;
import com.cag.twowheeler.dto.InseartVehicle;
import com.cag.twowheeler.dto.VehicalsAllData;
import com.cag.twowheeler.dto.VehiclePriceDetail;
import com.cag.twowheeler.entity.StateAbbreviation;
import com.cag.twowheeler.entity.VehicalOem;
import com.cag.twowheeler.entity.VehicalPrice;
import com.cag.twowheeler.entity.VehicalVariant;
import com.cag.twowheeler.entity.VehicleModal;
import com.cag.twowheeler.repository.StateAbbreviationRepository;
import com.cag.twowheeler.repository.VehicalOemRepository;
import com.cag.twowheeler.repository.VehicalPriceRepository;
import com.cag.twowheeler.repository.VehicalVariantRepository;
import com.cag.twowheeler.repository.VehicleModalRepository;
import com.cag.twowheeler.service.TwoWheelerService;

@Service
public class TwoWheelerServiceImpl implements TwoWheelerService {

	@Autowired
	private VehicalPriceRepository priceRepository;
	@Autowired
	private VehicalOemRepository oemRepositoryy;
	@Autowired
	private VehicalVariantRepository variantRepository;
	@Autowired
	private VehicleModalRepository vehicleModalRepository;
	@Autowired
	private StateAbbreviationRepository abbreviationRepository;

	@Override
	public VehiclePriceDetail getPrice(String vehicalState, String vehicalVeriant, String vehicalOem) {
		VehicalPrice vehicalprice = priceRepository.findByStateAndTypeVehicalvariantNameAndOemVehicalOem(vehicalState,
				vehicalVeriant, vehicalOem);
		if (vehicalprice != null) {
			VehiclePriceDetail priceDetail = new VehiclePriceDetail();
			priceDetail.setMaxLoanAmount(vehicalprice.getVehicalMaxLoanAmount());
			priceDetail.setOnRoadPrice(vehicalprice.getVehicalOnRoadPrice());
			return priceDetail;
		}
		return null;
	}

	@Override
	public List<VehicalsAllData> getAllVehicalData() {
		List<VehicalPrice> getAllvehicalData = priceRepository.findAll();
		List<VehicalsAllData> vehicalsAllDatas = new ArrayList<>();
		getAllvehicalData.stream().forEach(e -> {
			VehicalsAllData vehical = new VehicalsAllData();
			vehical.setVehicleId(e.getVehicalPriceID());
			vehical.setVehicleMaxLoanAmount(e.getVehicalMaxLoanAmount());
			vehical.setVehicalOnRoadPrice(e.getVehicalOnRoadPrice());
			vehical.setVehicleVariant(e.getType().getVehicalvariantName());
			vehical.setVehicleModel(e.getType().getVehicleModal().getVehicleModelName());
			vehical.setVehicalOem(e.getOem().getVehicalOem());
			vehical.setVehicalState(e.getState());
			vehicalsAllDatas.add(vehical);
		});
		// sorting Logic
		Comparator<VehicalsAllData> sorted = new Comparator<VehicalsAllData>() {
			@Override
			public int compare(VehicalsAllData o1, VehicalsAllData o2) {
				return o1.getVehicleModel().compareTo(o2.getVehicleModel());
			}
		};
		// sorted list base on Vehicle Name
		List<VehicalsAllData> allVehicalData = vehicalsAllDatas.stream().sorted(sorted).collect(Collectors.toList());
		return allVehicalData;
	}

	@Override
	public List<AllVehicleOem> getOem() {

		List<VehicalOem> vehicalOems = oemRepositoryy.findAll();
		List<AllVehicleOem> oems = new ArrayList<>();
		vehicalOems.stream().forEach(e -> {
			AllVehicleOem oem = new AllVehicleOem();
			oem.setOemId(e.getVehicalOemid());
			oem.setOemName(e.getVehicalOem());
			oems.add(oem);
		});
		return oems;
	}

	@Override
	public Boolean editVehicleData(String id, VehicalsAllData data) {
		Optional<VehicalPrice> vehicle = priceRepository.findById(id);
		if (vehicle.isPresent()) {
			VehicalPrice v1 = vehicle.get();
			v1.setVehicalOnRoadPrice(data.getVehicalOnRoadPrice());
			v1.setVehicalMaxLoanAmount(data.getVehicleMaxLoanAmount());
			v1.getType().setVehicalvariantName(data.getVehicleVariant());

			// As of Now Feature Not provided...
//			VehicleModal vehicleModal = v1.getType().getVehicleModal();
//			vehicleModal.setVehicleModelName(data.getVehicleModel());
//			v1.getType().setVehicleModal(vehicleModal);

//			v1.getOem().setVehicalOem(data.getVehicalOem());
//			v1.setState(data.getVehicalState());
			priceRepository.save(v1);
			return true;
		}

		return false;
	}

//	@Override
//	public String deleteData(int id) {
//		Optional<VehicalPrice> vehicle = priceRepository.findById(id);
//		if (vehicle.isPresent()) {
//			VehicalPrice vehicledata = vehicle.get();
//			List<VehicalPrice> allData = priceRepository.findAll();
//			long count = allData.stream()
//					.filter(e -> e.getType().getVehicalvariantName().equalsIgnoreCase(vehicledata.getType().getVehicalvariantName()))
//					.count();
//			if (!(count > 1))
//				priceRepository.delete(vehicledata);
//			else {
//				vehicledata.setType(null);
//				priceRepository.save(vehicledata);
//				priceRepository.delete(vehicledata);
//			}
//			return "Data Successfully Deleted";
//		}
//
//		return "not deleted";
//	}

	@Override
	public String addVehicle(InseartVehicle vehicle) {

		List<VehicalVariant> allVehicle = variantRepository.findAll();
		List<VehicalVariant> collect = new ArrayList<>();
		if (!allVehicle.isEmpty()) {
			collect = allVehicle.stream()
					.filter(e -> e.getVehicalvariantName().equalsIgnoreCase(vehicle.getVehicleVariant()))
					.collect(Collectors.toList());
		}

		if (!collect.isEmpty()) {
			boolean anyMatch = collect.get(0).getVehical_Price().stream()
					.anyMatch(e -> e.getState().equalsIgnoreCase(vehicle.getVehicleState()));
			if (anyMatch)
				return "Vehical Alrady Exist with Given State";
			else {
				VehicalPrice newVehicalData = new VehicalPrice();
				
				/**
				 * @ ID creation logic for Vehicle Price By State
				 */
				Optional<StateAbbreviation> optionalData = abbreviationRepository.findById(vehicle.getVehicleState());
				String vehiclePriceID=optionalData.get().getStateAbbreviation()+collect.get(0).getVehicalTypeid();
				// Seating Vehicle TypeID
				newVehicalData.setVehicalPriceID(vehiclePriceID);
				
				newVehicalData.setState(vehicle.getVehicleState());
				newVehicalData.setOem(collect.get(0).getOem());
				newVehicalData.setVehicalMaxLoanAmount(vehicle.getVehicleMaxLoanAmount());
				newVehicalData.setVehicalOnRoadPrice(vehicle.getVehicalOnRoadPrice());
				newVehicalData.setType(collect.get(0));
				priceRepository.save(newVehicalData);

				return "Existing Vehicle Data Add Successfully With Other State";
			}

		} else {
			List<VehicalOem> findByOemName = oemRepositoryy.findByVehicalOem(vehicle.getVehicleOem());
			VehicalVariant newVehicle = new VehicalVariant();

			/**
			 * @ ID creation logic for Vehicle Variant
			 */
			String VehicleVariantID = findByOemName.get(0).getVehicalOem().substring(0, 3) + "-" + "__";
			String updatedID = "";
			List<String> similarVariantId = new ArrayList<>();
			similarVariantId = variantRepository.getSimilarVariantId(VehicleVariantID);
			if (!similarVariantId.isEmpty()) {
				List<String> Ids = similarVariantId.stream().sorted().collect(Collectors.toList());
				String variantId = Ids.get(Ids.size() - 1);
				String variantCount = variantId.substring(4);
				char[] array = variantCount.toCharArray();
				int value = Integer.parseInt(variantCount);
				value++;
				String str = "" + value;
				int len = array.length - 1;
				for (int i = str.length() - 1; i >= 0; i--) {
					array[len] = str.charAt(i);
					len--;
				}
				String updatedValue = new String(array);
				updatedID += findByOemName.get(0).getVehicalOem().substring(0, 3) + "-" + updatedValue;
			} else {
				updatedID += findByOemName.get(0).getVehicalOem().substring(0, 3) + "-01";
			}
			newVehicle.setVehicalTypeid(updatedID);
			newVehicle.setOem(findByOemName.get(0));
			newVehicle.setVehicalvariantName(vehicle.getVehicleVariant());
			VehicleModal vehicleModal = vehicleModalRepository.findByVehicleModelName(vehicle.getVehicleModel());
			if (vehicleModal != null)
				newVehicle.setVehicleModal(vehicleModal);
			else {
				VehicleModal newVehicleModal = new VehicleModal();

				/**
				 * @ ID creation logic for VehicleModal
				 */
				String id = newVehicle.getOem().getVehicalOem().substring(0, 3) + "-M" + "__";
				String incrementalID = "";
				List<String> similarModalId = new ArrayList<>();
				similarModalId = vehicleModalRepository.getSimilarModalId(id);
				if (!similarModalId.isEmpty()) {
					List<String> sortedIds = similarModalId.stream().sorted().collect(Collectors.toList());
					String idData = sortedIds.get(sortedIds.size() - 1);
					String count = idData.substring(5);
					char[] charArray = count.toCharArray();
					int num = Integer.parseInt(count);
					num++;
					String arr = "" + num;
					int temp = charArray.length - 1;
					for (int i = arr.length() - 1; i >= 0; i--) {
						charArray[temp] = arr.charAt(i);
						temp--;
					}
					String updatedcount = new String(charArray);
					incrementalID += newVehicle.getOem().getVehicalOem().substring(0, 3) + "-M" + updatedcount;
				} else {
					incrementalID += newVehicle.getOem().getVehicalOem().substring(0, 3) + "-M01";
				}

				newVehicleModal.setVehicleModelId(incrementalID);
				newVehicleModal.setVehicleModelName(vehicle.getVehicleModel());
				vehicleModalRepository.save(newVehicleModal);
				// get VehicalModel updated Object for mapping
				VehicleModal getNewvehicleModal = vehicleModalRepository
						.findByVehicleModelName(vehicle.getVehicleModel());
				newVehicle.setVehicleModal(getNewvehicleModal);
			}
			variantRepository.save(newVehicle);

			VehicalPrice newVehicalPrice = new VehicalPrice();
			Optional<StateAbbreviation> optionalData = abbreviationRepository.findById(vehicle.getVehicleState());
			VehicalVariant vehicalVariant = variantRepository.findAll().stream()
					.filter(e -> e.getVehicalvariantName().equalsIgnoreCase(vehicle.getVehicleVariant()))
					.collect(Collectors.toList()).get(0);
			
			/**
			 * @ ID creation logic for Vehicle price by state
			 */
			String priceVariantId = optionalData.get().getStateAbbreviation() + vehicalVariant.getVehicalTypeid();
			newVehicalPrice.setVehicalPriceID(priceVariantId);
			newVehicalPrice.setState(vehicle.getVehicleState());
			newVehicalPrice.setOem(findByOemName.get(0));
			newVehicalPrice.setVehicalMaxLoanAmount(vehicle.getVehicleMaxLoanAmount());
			newVehicalPrice.setVehicalOnRoadPrice(vehicle.getVehicalOnRoadPrice());
			newVehicalPrice.setType(vehicalVariant);

			priceRepository.save(newVehicalPrice);
			
			return "New Vehicle Data Add Successfully";
		}
	}

	@Override
	public String deleteData(int id) {
		// TODO Auto-generated method stub
		return "Logic Not preseant";
	}

}
