package com.cag.twowheeler.service.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cag.twowheeler.dto.BranchDto;
import com.cag.twowheeler.dto.MainDealerDetailsDto;
import com.cag.twowheeler.dto.SubDealerDetailsDto;
import com.cag.twowheeler.dto.VehicleVariantDto;
import com.cag.twowheeler.entity.Branch;
import com.cag.twowheeler.entity.MainDealer;
import com.cag.twowheeler.entity.StateAbbreviation;
import com.cag.twowheeler.entity.SubDealer;
import com.cag.twowheeler.entity.VehicalPrice;
import com.cag.twowheeler.repository.BranchRepository;
import com.cag.twowheeler.repository.MainDealerRepository;
import com.cag.twowheeler.repository.StateAbbreviationRepository;
import com.cag.twowheeler.repository.SubDealerRepository;
import com.cag.twowheeler.repository.VehicalPriceRepository;
import com.cag.twowheeler.repository.VehicalVariantRepository;
import com.cag.twowheeler.service.TwoWheelerDealerService;

@Service
public class TwoWheelerDealerServiceImpl implements TwoWheelerDealerService {

	@Autowired
	MainDealerRepository mainDealerRepository;
	@Autowired
	SubDealerRepository subDealerRepository;
	@Autowired
	StateAbbreviationRepository abbreviationRepository;
	@Autowired
	VehicalVariantRepository variantRepository;
	@Autowired
	VehicalPriceRepository vehicalPriceRepository;
	@Autowired
	BranchRepository branchRepository;


	@Override
	public List<MainDealerDetailsDto> getMainDealersDetails() {
		List<MainDealer> allMainDealers = mainDealerRepository.findAll();
		List<MainDealerDetailsDto> dealerDetailsDtos = new ArrayList<>();
		if (!allMainDealers.isEmpty()) {
			allMainDealers.stream().forEach(e -> {

				String a="jbsb";
				String aa="jbjnbj";
				String aa="jbjnbj";
				String aa="jbjnbj";
				String aa="jbjnbj";
				String aa="jbjnbj";
				MainDealerDetailsDto dealerDetailsDto = MainDealerDetailsDto.builder()
						.mainDealerAccountHolderName(e.getAccountHolderName())
						.mainDealerActivationStatus(e.getActivatioinStatus()).mainDealerStatus(e.getStatus())
						.mainDealerActivationData(e.getActivationData()).mainDealerExpireData(e.getExpiryDate())
						.mainDealerAlternateContactNumber(e.getAlternateContactNumber())
						.mainDealerBankAccNumber(e.getBankAccNumber()).mainDealerBankBranchName(e.getBankBranchName())
						.mainDealerBankName(e.getBankName()).mainDealerContactNumber(e.getContactNumber())
						.mainDealerContactPersonName(e.getContactPersonName()).mainDealerGstNumber(e.getGstNumber())
						.mainDealerIfsc(e.getIfsc()).mainDealerMailID(e.getMailID())
						.mainDealerManufacturerName(e.getManufacturerName()).mainDealerName(e.getDealerName())
						.mainDealerPanNumber(e.getPanNumber()).mainDealerPaniniCheck(e.getPaniniCheck())
						.mainDealerID(e.getMainDealerID()).addressDetails(e.getAddressDetails()).city(e.getCity())
						.contactPersonMobile(e.getContactPersonMobile()).district(e.getDistrict()).state(e.getState())
						.pinCode(e.getPinCode()).build();
				dealerDetailsDtos.add(dealerDetailsDto);
			});
			return dealerDetailsDtos;
		}
		return dealerDetailsDtos;
	}

	@Override
	public List<SubDealerDetailsDto> getSubDealerDetails(String mainDealerId) {
		Optional<MainDealer> mainDealer = mainDealerRepository.findById(mainDealerId);
		new ArrayList<>();
		if (mainDealer.isPresent()) {
			MainDealer mainDealerInfo = mainDealer.get();
			List<SubDealer> subDealers = mainDealerInfo.getSubDealer();
			List<SubDealerDetailsDto> allSubDealers = new ArrayList<>();
			subDealers.stream().forEach(a -> {

				SubDealerDetailsDto subDealerDto = SubDealerDetailsDto.builder()
						.subDealerAccountHolderName(a.getAccountHolderName())
						.subDealerActivationStatus(a.getActivatioinStatus())
						.subDealerActivationData(a.getActivationData()).subDealerExpireData(a.getExpiryDate())
						.subDealerAlternateContactNumber(a.getAlternateContactNumber())
						.subDealerBankAccNumber(a.getBankAccNumber()).subDealerBankBranchName(a.getBankBranchName())
						.subDealerBankName(a.getBankName()).subDealerContactNumber(a.getContactNumber())
						.subDealerContactPersonName(a.getContactPersonName()).subDealerMailID(a.getMailID())
						.subDealerName(a.getDealerName()).subDealerGstNumber(a.getGstNumber())
						.subDealerID(a.getSubDealerID()).subDealerIfsc(a.getIfsc())
						.subDealerManufacturerName(a.getManufacturerName()).subDealerPanNumber(a.getPanNumber())
						.subDealerPaniniCheck(a.getPaniniCheck()).addressDetails(a.getAddressDetails())
						.city(a.getCity()).contactPersonMobile(a.getContactPersonMobile()).district(a.getDistrict())
						.pinCode(a.getPinCode()).state(a.getState()).build();

				allSubDealers.add(subDealerDto);

			});
			return allSubDealers;
		}
		return new ArrayList<>();
	}

	/**
	 * Get Main Dealer By Search/Filter (State or District)
	 */
	@Override
	public List<MainDealerDetailsDto> getMainDealer(String state, String district) {
		List<MainDealerDetailsDto> maindealers = new ArrayList<>();
		if (state == null & district != null) {
			List<MainDealer> allMainDealer = mainDealerRepository.findByDistrict(district);
			if (!allMainDealer.isEmpty()) {
				allMainDealer.stream().forEach(e -> {
					MainDealerDetailsDto dealerDetailsDto = MainDealerDetailsDto.builder()
							.mainDealerAccountHolderName(e.getAccountHolderName())
							.mainDealerActivationStatus(e.getActivatioinStatus())
							.mainDealerActivationData(e.getActivationData()).mainDealerExpireData(e.getExpiryDate())
							.mainDealerAlternateContactNumber(e.getAlternateContactNumber())
							.mainDealerBankAccNumber(e.getBankAccNumber())
							.mainDealerBankBranchName(e.getBankBranchName()).mainDealerBankName(e.getBankName())
							.mainDealerContactNumber(e.getContactNumber())
							.mainDealerContactPersonName(e.getContactPersonName()).mainDealerGstNumber(e.getGstNumber())
							.mainDealerIfsc(e.getIfsc()).mainDealerMailID(e.getMailID())
							.mainDealerManufacturerName(e.getManufacturerName()).mainDealerName(e.getDealerName())
							.mainDealerPanNumber(e.getPanNumber()).mainDealerPaniniCheck(e.getPaniniCheck())
							.mainDealerID(e.getMainDealerID()).addressDetails(e.getAddressDetails()).city(e.getCity())
							.contactPersonMobile(e.getContactPersonMobile()).district(e.getDistrict())
							.state(e.getState()).pinCode(e.getPinCode()).build();
					maindealers.add(dealerDetailsDto);
				});
				return maindealers;
			}
			return new ArrayList<>();
		} else if (state != null & district == null) {
			List<MainDealer> allMainDealer = mainDealerRepository.findByState(state);
			if (!allMainDealer.isEmpty()) {
				allMainDealer.stream().forEach(e -> {

					MainDealerDetailsDto dealerDetailsDto = MainDealerDetailsDto.builder()
							.mainDealerAccountHolderName(e.getAccountHolderName())
							.mainDealerActivationStatus(e.getActivatioinStatus())
							.mainDealerActivationData(e.getActivationData()).mainDealerExpireData(e.getExpiryDate())
							.mainDealerAlternateContactNumber(e.getAlternateContactNumber())
							.mainDealerBankAccNumber(e.getBankAccNumber())
							.mainDealerBankBranchName(e.getBankBranchName()).mainDealerBankName(e.getBankName())
							.mainDealerContactNumber(e.getContactNumber())
							.mainDealerContactPersonName(e.getContactPersonName()).mainDealerGstNumber(e.getGstNumber())
							.mainDealerIfsc(e.getIfsc()).mainDealerMailID(e.getMailID())
							.mainDealerManufacturerName(e.getManufacturerName()).mainDealerName(e.getDealerName())
							.mainDealerPanNumber(e.getPanNumber()).mainDealerPaniniCheck(e.getPaniniCheck())
							.mainDealerID(e.getMainDealerID()).addressDetails(e.getAddressDetails()).city(e.getCity())
							.contactPersonMobile(e.getContactPersonMobile()).district(e.getDistrict())
							.state(e.getState()).pinCode(e.getPinCode()).build();
					maindealers.add(dealerDetailsDto);
				});
				return maindealers;
			}
			return new ArrayList<>();
		} else {
			List<MainDealer> allMainDealer = mainDealerRepository.findByStateAndDistrict(state, district);
			if (!allMainDealer.isEmpty()) {
				allMainDealer.stream().forEach(e -> {
					MainDealerDetailsDto dealerDetailsDto = MainDealerDetailsDto.builder()
							.mainDealerAccountHolderName(e.getAccountHolderName())
							.mainDealerActivationStatus(e.getActivatioinStatus())
							.mainDealerActivationData(e.getActivationData()).mainDealerExpireData(e.getExpiryDate())
							.mainDealerAlternateContactNumber(e.getAlternateContactNumber())
							.mainDealerBankAccNumber(e.getBankAccNumber())
							.mainDealerBankBranchName(e.getBankBranchName()).mainDealerBankName(e.getBankName())
							.mainDealerContactNumber(e.getContactNumber())
							.mainDealerContactPersonName(e.getContactPersonName()).mainDealerGstNumber(e.getGstNumber())
							.mainDealerIfsc(e.getIfsc()).mainDealerMailID(e.getMailID())
							.mainDealerManufacturerName(e.getManufacturerName()).mainDealerName(e.getDealerName())
							.mainDealerPanNumber(e.getPanNumber()).mainDealerPaniniCheck(e.getPaniniCheck())
							.mainDealerID(e.getMainDealerID()).addressDetails(e.getAddressDetails()).city(e.getCity())
							.contactPersonMobile(e.getContactPersonMobile()).district(e.getDistrict())
							.state(e.getState()).pinCode(e.getPinCode()).build();
					maindealers.add(dealerDetailsDto);
				});
				return maindealers;
			}
			return new ArrayList<>();
		}
	}

	/**
	 * @Api for Adding MainDealer
	 */
	@Override
	public Boolean addMainDealer(MainDealerDetailsDto dealer) {
		if (dealer != null) {

			// Get State Abbreviation
			Optional<StateAbbreviation> abbreviation = abbreviationRepository.findById(dealer.getState());
			StateAbbreviation stateAbbreviation = abbreviation.get();
			String state = stateAbbreviation.getStateAbbreviation();

			// main dealer IdGeneratation
			String id = "";
			id += state + dealer.getMainDealerManufacturerName().subSequence(0, 3) + "-" + "____" + "-A01";
			List<String> similarDealerId = new ArrayList<>();
			similarDealerId = mainDealerRepository.getSimilarDealerId(id);
			String incrementId = null;
			if (!similarDealerId.isEmpty()) {
				List<String> sortedId = similarDealerId.stream().sorted().collect(Collectors.toList());

				String dealerid = sortedId.get(sortedId.size() - 1);
				String dealercount = dealerid.substring(6, 10);
				char[] charArray = dealercount.toCharArray();
				int count = Integer.parseInt(dealercount);
				count++;
				String arr = "" + count;
				int temp = charArray.length - 1;
				for (int i = arr.length() - 1; i >= 0; i--) {
					charArray[temp] = arr.charAt(i);
					temp--;
				}
				String updatedCount = new String(charArray);
				incrementId = state + dealer.getMainDealerManufacturerName().subSequence(0, 3) + "-" + updatedCount
						+ "-A01";
			} else {
				incrementId = state + dealer.getMainDealerManufacturerName().subSequence(0, 3) + "-0001-A01";
			}
			// mainDealer Object Set
			MainDealer mainDealer = MainDealer.builder().mainDealerID(incrementId).status("Pending")
					.activatioinStatus(dealer.getMainDealerActivationStatus())
					.accountHolderName(dealer.getMainDealerAccountHolderName())
					.activationData(dealer.getMainDealerActivationData()).expiryDate(dealer.getMainDealerExpireData())
					.addressDetails(dealer.getAddressDetails())
					.alternateContactNumber(dealer.getMainDealerAlternateContactNumber())
					.bankAccNumber(dealer.getMainDealerBankAccNumber())
					.bankBranchName(dealer.getMainDealerBankBranchName()).bankName(dealer.getMainDealerBankName())
					.contactNumber(dealer.getMainDealerContactNumber())
					.contactPersonMobile(dealer.getContactPersonMobile())
					.contactPersonName(dealer.getMainDealerContactPersonName()).dealerName(dealer.getMainDealerName())
					.delearType("MAIN").district(dealer.getDistrict()).city(dealer.getCity())
					.ifsc(dealer.getMainDealerIfsc()).gstNumber(dealer.getMainDealerGstNumber())
					.mailID(dealer.getMainDealerMailID()).manufacturerName(dealer.getMainDealerManufacturerName())
					.state(dealer.getState()).pinCode(dealer.getPinCode())
					.paniniCheck(dealer.getMainDealerPaniniCheck()).panNumber(dealer.getMainDealerPanNumber()).build();
			mainDealerRepository.save(mainDealer);
			return true;
		}
		return false;
	}

	/**
	 * @Api For Adding SubDealer
	 */
	@Override
	public Boolean addSubDealers(String mainDealerID, SubDealerDetailsDto subDealerdto) {
		// Get Main Dealer
		Optional<MainDealer> dealer = mainDealerRepository.findById(mainDealerID);
		if (dealer.isPresent()) {

			MainDealer mainDealer = dealer.get(); // MainDealer Reference Set in SubDealer Object
			//
			String dealerID = mainDealer.getMainDealerID();
			String maincount = dealerID.substring(6, 10);

			// Get State Abbreviation
			Optional<StateAbbreviation> abbreviation = abbreviationRepository.findById(mainDealer.getState());
			StateAbbreviation stateAbbreviation = abbreviation.get();
			String state = stateAbbreviation.getStateAbbreviation();

			String id = "";
			String incrementId = null;// locally Variable
			id += state + mainDealer.getManufacturerName().subSequence(0, 3) + "-" + maincount + "-S" + "__";
			List<String> similarDealerId = new ArrayList<>();
			similarDealerId = subDealerRepository.getSimilarDealerId(id);
			if (!similarDealerId.isEmpty()) {
				List<String> sortedId = similarDealerId.stream().sorted().collect(Collectors.toList());
				String priviousId = sortedId.get(sortedId.size() - 1);
				String count = priviousId.substring(12);
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
				incrementId = state + mainDealer.getManufacturerName().subSequence(0, 3) + "-" + maincount + "-S"
						+ updatedcount;
			} else {
				incrementId = state + mainDealer.getManufacturerName().subSequence(0, 3) + "-" + maincount + "-S01";
			}
			// Seating SubDealer
			SubDealer subDealer = SubDealer.builder().subDealerID(incrementId)
					.accountHolderName(subDealerdto.getSubDealerAccountHolderName())
					.activationData(subDealerdto.getSubDealerActivationData())
					.expiryDate(subDealerdto.getSubDealerExpireData()).status("Pending")
					.activatioinStatus(subDealerdto.getSubDealerActivationStatus())
					.addressDetails(subDealerdto.getAddressDetails())
					.alternateContactNumber(subDealerdto.getSubDealerAlternateContactNumber())
					.bankAccNumber(subDealerdto.getSubDealerBankAccNumber())
					.bankName(subDealerdto.getSubDealerBankName())
					.bankBranchName(subDealerdto.getSubDealerBankBranchName()).city(subDealerdto.getCity())
					.contactNumber(subDealerdto.getSubDealerContactNumber())
					.contactPersonMobile(subDealerdto.getContactPersonMobile())
					.contactPersonName(subDealerdto.getSubDealerContactPersonName())
					.dealerName(subDealerdto.getSubDealerName()).delearType("SUB").district(mainDealer.getDistrict())
					.gstNumber(subDealerdto.getSubDealerGstNumber()).ifsc(subDealerdto.getSubDealerIfsc())
					.mailID(subDealerdto.getSubDealerMailID()).mainDealers(mainDealer)
					.manufacturerName(subDealerdto.getSubDealerManufacturerName())
					.panNumber(subDealerdto.getSubDealerPanNumber()).paniniCheck(subDealerdto.getSubDealerPaniniCheck())
					.pinCode(subDealerdto.getPinCode()).state(mainDealer.getState()).build();

			subDealerRepository.save(subDealer);
			return true;
		}
		return false;
	}

	@Override
	public Boolean editMainDealer(String mainDealerId, MainDealerDetailsDto mainDetailsDto) {
		if (mainDetailsDto != null) {
			Optional<MainDealer> dealer = mainDealerRepository.findById(mainDealerId);
			if (dealer.isPresent()) {
				MainDealer mainDealer = dealer.get();
				MainDealer newMainDealer = MainDealer.builder().status("Updated")
						.activatioinStatus(mainDetailsDto.getMainDealerActivationStatus())
						.accountHolderName(mainDetailsDto.getMainDealerAccountHolderName())
						.activationData(mainDetailsDto.getMainDealerActivationData())
						.expiryDate(mainDetailsDto.getMainDealerExpireData())
						.alternateContactNumber(mainDetailsDto.getMainDealerAlternateContactNumber())
						.bankAccNumber(mainDetailsDto.getMainDealerBankAccNumber())
						.bankBranchName(mainDetailsDto.getMainDealerBankBranchName())
						.bankName(mainDetailsDto.getMainDealerBankName())
						.contactNumber(mainDetailsDto.getMainDealerContactNumber())
						.contactPersonName(mainDetailsDto.getMainDealerContactPersonName())
						.dealerName(mainDetailsDto.getMainDealerName())
						.gstNumber(mainDetailsDto.getMainDealerGstNumber()).ifsc(mainDetailsDto.getMainDealerIfsc())
						.mailID(mainDetailsDto.getMainDealerMailID())
						.manufacturerName(mainDetailsDto.getMainDealerManufacturerName())
						.panNumber(mainDetailsDto.getMainDealerPanNumber())
						.paniniCheck(mainDetailsDto.getMainDealerPaniniCheck())
						.addressDetails(mainDetailsDto.getAddressDetails()).state(mainDetailsDto.getState())
						.district(mainDetailsDto.getDistrict()).pinCode(mainDetailsDto.getPinCode())
						.contactPersonMobile(mainDetailsDto.getContactPersonMobile()).build();
				BeanUtils.copyProperties(newMainDealer, mainDealer); // Hear Id updated to Zero (---v)
				mainDealer.setMainDealerID(mainDealerId); // Seating original Id {Because it will Be zero }
				mainDealerRepository.save(mainDealer);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean editSubDealer(String subDealerId, SubDealerDetailsDto subDetailsDto) {
		if (subDetailsDto != null) {
			Optional<SubDealer> dealer = subDealerRepository.findById(subDealerId);
			if (dealer.isPresent()) {
				SubDealer subDealer = dealer.get();

				MainDealer mainDealer = subDealer.getMainDealers();

				SubDealer newSubDealer = SubDealer.builder().status("Updated")
						.activatioinStatus(subDetailsDto.getSubDealerActivationStatus())
						.accountHolderName(subDetailsDto.getSubDealerAccountHolderName())
						.activationData(subDetailsDto.getSubDealerActivationData())
						.expiryDate(subDetailsDto.getSubDealerExpireData())
						.alternateContactNumber(subDetailsDto.getSubDealerAlternateContactNumber())
						.bankAccNumber(subDetailsDto.getSubDealerBankAccNumber())
						.bankBranchName(subDetailsDto.getSubDealerBankBranchName())
						.bankName(subDetailsDto.getSubDealerBankName())
						.contactNumber(subDetailsDto.getSubDealerContactNumber())
						.contactPersonName(subDetailsDto.getSubDealerContactPersonName())
						.dealerName(subDetailsDto.getSubDealerName()).gstNumber(subDetailsDto.getSubDealerGstNumber())
						.ifsc(subDetailsDto.getSubDealerIfsc()).mailID(subDetailsDto.getSubDealerMailID())
						.manufacturerName(subDetailsDto.getSubDealerManufacturerName())
						.panNumber(subDetailsDto.getSubDealerPanNumber())
						.paniniCheck(subDetailsDto.getSubDealerPaniniCheck()).mainDealers(mainDealer)
						.contactPersonMobile(subDetailsDto.getContactPersonMobile()).state(subDetailsDto.getState())
						.city(subDetailsDto.getCity()).district(subDetailsDto.getDistrict())
						.pinCode(subDetailsDto.getPinCode()).addressDetails(subDetailsDto.getAddressDetails()).build();
				BeanUtils.copyProperties(newSubDealer, subDealer);// Hear Id updated to null
				subDealer.setSubDealerID(subDealerId);
				subDealerRepository.save(subDealer);// save Update Sub_Dealer
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public List<String> getStates() {
		return mainDealerRepository.findAll().stream().map(e -> e.getState()).distinct().filter(e -> e != null)
				.toList();
	}

	@Override
	public List<String> getDistricts() {
		return mainDealerRepository.findAll().stream().map(e -> e.getDistrict()).distinct().filter(e -> e != null)
				.toList();
	}

	@Override
	public List<String> getOemBaseOnState(String state) {
		return vehicalPriceRepository.findByState(state);
	}

	@Override
	public List<MainDealerDetailsDto> getExcelFile() {
		List<MainDealer> allDealer = new ArrayList<>();
		allDealer = mainDealerRepository.findAll();
		List<MainDealerDetailsDto> allMainDealerdto = new ArrayList<>();
		if (!allDealer.isEmpty()) {
			//.filter(mainstatus -> mainstatus.getStatus().equalsIgnoreCase("Approved"))
			allDealer.stream().forEach(e -> {

				List<SubDealer> allSubDealer = new ArrayList<>();
				List<SubDealerDetailsDto> subDealerDtos = new ArrayList<>();

				allSubDealer = e.getSubDealer();

				if (!allSubDealer.isEmpty()) {
					allSubDealer.stream().filter(substatus -> substatus.getStatus().equalsIgnoreCase("Approved"))
							.forEach(data -> {
								SubDealerDetailsDto subDealerDto = SubDealerDetailsDto.builder()
										.subDealerAccountHolderName(data.getAccountHolderName())
										.subDealerActivationData(e.getActivationData())
										.subDealerExpireData(e.getExpiryDate())
										.subDealerAlternateContactNumber(data.getAlternateContactNumber())
										.subDealerBankAccNumber(data.getBankAccNumber())
										.subDealerBankBranchName(data.getBankBranchName())
										.subDealerBankName(data.getBankName())
										.subDealerContactNumber(data.getContactNumber())
										.subDealerContactPersonName(data.getContactPersonName())
										.subDealerMailID(data.getMailID()).subDealerName(data.getDealerName())
										.subDealerGstNumber(data.getGstNumber()).subDealerID(data.getSubDealerID())
										.subDealerIfsc(data.getIfsc())
										.subDealerManufacturerName(data.getManufacturerName())
										.subDealerPanNumber(data.getPanNumber())
										.subDealerPaniniCheck(e.getPaniniCheck())
										.addressDetails(data.getAddressDetails()).city(data.getCity())
										.contactPersonMobile(data.getContactPersonMobile()).district(data.getDistrict())
										.pinCode(data.getPinCode()).state(data.getState())
										.mainDealer(data.getMainDealers()).build();
								subDealerDtos.add(subDealerDto);
							});
				}
				MainDealerDetailsDto dealerDetailsDto = MainDealerDetailsDto.builder()
						.mainDealerAccountHolderName(e.getAccountHolderName())
						.mainDealerActivationData(e.getActivationData()).mainDealerExpireData(e.getExpiryDate())
						.mainDealerAlternateContactNumber(e.getAlternateContactNumber())
						.mainDealerBankAccNumber(e.getBankAccNumber()).mainDealerBankBranchName(e.getBankBranchName())
						.mainDealerBankName(e.getBankName()).mainDealerContactNumber(e.getContactNumber())
						.mainDealerContactPersonName(e.getContactPersonName()).mainDealerGstNumber(e.getGstNumber())
						.mainDealerIfsc(e.getIfsc()).mainDealerMailID(e.getMailID())
						.mainDealerManufacturerName(e.getManufacturerName()).mainDealerName(e.getDealerName())
						.mainDealerPanNumber(e.getPanNumber()).mainDealerPaniniCheck(e.getPaniniCheck())
						.mainDealerID(e.getMainDealerID()).addressDetails(e.getAddressDetails()).city(e.getCity())
						.contactPersonMobile(e.getContactPersonMobile()).district(e.getDistrict()).state(e.getState())
						.pinCode(e.getPinCode()).subDealerDetailsDtos(subDealerDtos).build();
				allMainDealerdto.add(dealerDetailsDto);
			});
			return allMainDealerdto;
		}
		return allMainDealerdto;
	}

	// ===============================Variants================================================

	@Override
	public List<VehicleVariantDto> mainVariants(String mainDealerID) {
		// Create List For Return Statement
		List<VehicleVariantDto> vehicleVariantDto = new ArrayList<VehicleVariantDto>();

		Optional<MainDealer> dealer = mainDealerRepository.findById(mainDealerID);
		if (dealer.isPresent()) {
			String manufacturerName = dealer.get().getManufacturerName();
			List<VehicalPrice> allVeriants = vehicalPriceRepository.findByOemVehicalOem(manufacturerName);
			if (!allVeriants.isEmpty()) {
				allVeriants.stream().filter(e -> e.getState().equalsIgnoreCase(dealer.get().getState())).forEach(e -> {
					VehicleVariantDto vehicleVariant = new VehicleVariantDto();
					vehicleVariant.setVariantID(e.getVehicalPriceID());
					vehicleVariant.setVariantName(e.getType().getVehicalvariantName());
					List<VehicalPrice> veriants = dealer.get().getVeriants();
					if (!veriants.contains(e))
						vehicleVariantDto.add(vehicleVariant);
				});
			}
			return vehicleVariantDto;
		}
		return vehicleVariantDto;
	}

	@Override
	public List<VehicleVariantDto> avaliableMainVariants(String mainDealerID) {
		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<VehicalPrice> veriants = mainDealer.getVeriants();
		ArrayList<VehicleVariantDto> avaliableVariants = new ArrayList<VehicleVariantDto>();
		if (!veriants.isEmpty()) {
			veriants.stream().forEach(e -> {
				VehicleVariantDto vehicleVariantDto = new VehicleVariantDto();
				vehicleVariantDto.setVariantID(e.getVehicalPriceID());
				vehicleVariantDto.setVariantName(e.getType().getVehicalvariantName());
				avaliableVariants.add(vehicleVariantDto);
			});
		}
		return avaliableVariants;
	}

	@Override
	public boolean addVeriantsMian(String mainDealerID, List<VehicleVariantDto> variants) {
		Optional<MainDealer> dealer = mainDealerRepository.findById(mainDealerID);
		if (dealer.isPresent()) {
			MainDealer mainDealer = dealer.get();
			List<VehicalPrice> varientsList = mainDealer.getVeriants();
			variants.stream().forEach(e -> {
				Optional<VehicalPrice> optionalData = vehicalPriceRepository.findById(e.getVariantID());
				if (optionalData.isPresent()) {
					VehicalPrice vehicalPriceData = optionalData.get();
					if (!varientsList.contains(vehicalPriceData))
						varientsList.add(vehicalPriceData);
				}
			});
//			List<VehicalPrice> collect = varientsList.stream().distinct().collect(Collectors.toList());)
			mainDealer.setVeriants(varientsList);
			mainDealerRepository.save(mainDealer);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean removeMainVariant(String mainDealerID, List<VehicleVariantDto> variants) {
		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<VehicalPrice> veriants = mainDealer.getVeriants();
		variants.stream().forEach(e -> {
			Optional<VehicalPrice> optionalData = vehicalPriceRepository.findById(e.getVariantID());
			if (optionalData.isPresent()) {
				VehicalPrice vehicalPrice = optionalData.get();
				veriants.remove(vehicalPrice);
			}
		});
		mainDealer.setVeriants(veriants);
		MainDealer responce = mainDealerRepository.save(mainDealer);
		if (responce != null)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	@Override
	public List<VehicleVariantDto> subVariants(String subDealerID) {
		// Create Empty DtoList For Return
		ArrayList<VehicleVariantDto> varients = new ArrayList<VehicleVariantDto>();

		// Get Sub_Dealer BY SubDealerID
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();

		// Get SubDealer Existing Variants
		List<VehicalPrice> subDealerVeriants = subDealer.getVehicleVeriants();

		MainDealer mainDealer = mainDealerRepository.findById(subDealer.getMainDealers().getMainDealerID()).get();

		// Get Main Dealer Existing Variants
		List<VehicalPrice> veriants = mainDealer.getVeriants();

		veriants.stream().forEach(e -> {
			if (!subDealerVeriants.contains(e)) {
				VehicleVariantDto vehicleVariantDto = new VehicleVariantDto();
				vehicleVariantDto.setVariantID(e.getVehicalPriceID());
				vehicleVariantDto.setVariantName(e.getType().getVehicalvariantName());
				varients.add(vehicleVariantDto);
			}
		});
		return varients;
	}

	@Override
	public List<VehicleVariantDto> avaliableSubVariants(String subDealerID) {
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
		List<VehicalPrice> veriants = subDealer.getVehicleVeriants();
		ArrayList<VehicleVariantDto> avaliableVariants = new ArrayList<VehicleVariantDto>();
		if (!veriants.isEmpty()) {
			veriants.stream().forEach(e -> {
				VehicleVariantDto vehicleVariantDto = new VehicleVariantDto();
				vehicleVariantDto.setVariantID(e.getVehicalPriceID());
				vehicleVariantDto.setVariantName(e.getType().getVehicalvariantName());
				avaliableVariants.add(vehicleVariantDto);
			});
		}
		return avaliableVariants;
	}

	@Override
	public boolean addVariantSub(String subDealerID, List<VehicleVariantDto> variants) {
		// Get SubDealer By SubDealerID
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
		List<VehicalPrice> subVeriants = subDealer.getVehicleVeriants();
		variants.stream().forEach(e -> {
			VehicalPrice vehicalPriceData = vehicalPriceRepository.findById(e.getVariantID()).get();
			if (!subVeriants.contains(vehicalPriceData))
				subVeriants.add(vehicalPriceData);
		});
		subDealer.setVehicleVeriants(subVeriants);
		subDealerRepository.save(subDealer);
		return Boolean.TRUE;
	}

	@Override
	public Boolean removeSubVariant(String subDealerID, List<VehicleVariantDto> variants) {
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
		List<VehicalPrice> vehicleVeriants = subDealer.getVehicleVeriants();
		variants.stream().forEach(e -> {
			Optional<VehicalPrice> optionalData = vehicalPriceRepository.findById(e.getVariantID());
			if (optionalData.isPresent()) {
				VehicalPrice vehicalPrice = optionalData.get();
				vehicleVeriants.remove(vehicalPrice);
			}
		});
		subDealer.setVehicleVeriants(vehicleVeriants);
		SubDealer response = subDealerRepository.save(subDealer);
		if (response != null)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

//===============================Branches============================================
	@Override
	public String addMainBranches(String mainDealerID, List<BranchDto> branchs) {
		// Get Main Dealer By ID
		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<Branch> mainBranches = mainDealer.getMainBranches();
		if (!branchs.isEmpty()) {
			branchs.stream().forEach(e -> {
				Branch branch = branchRepository.findById(e.getBranchID()).get();
				if (!mainBranches.contains(branch))
					mainBranches.add(branch);
			});
			mainDealer.setMainBranches(mainBranches);
			mainDealerRepository.save(mainDealer);
			return "Sucessfully Added";
		}
		return "Not Added";
	}

	@Override
	public Boolean removeMainBranches(String mainDealerID, List<BranchDto> branchs) {
		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<Branch> mainBranches = mainDealer.getMainBranches();
		if (!branchs.isEmpty()) {
			branchs.stream().forEach(e -> {
				Branch branch = branchRepository.findById(e.getBranchID()).get();
				if (mainBranches.contains(branch)) {
					mainBranches.remove(branch);
				}
			});
			mainDealer.setMainBranches(mainBranches);
			mainDealerRepository.save(mainDealer);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Set<BranchDto> showMainBranches(String mainDealerID) {
		Set<BranchDto> notContainMainBranches = new LinkedHashSet<BranchDto>();

		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<Branch> allBranches = branchRepository.findByState(mainDealer.getState());
		List<Branch> mainBranches = mainDealer.getMainBranches();

		if (!allBranches.isEmpty()) {
			allBranches.stream().forEach(e -> {
				if (!mainBranches.contains(e)) {
					BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
							.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
					notContainMainBranches.add(branchDto);
				}
			});
		}
		return notContainMainBranches;
	}

	@Override
	public Set<BranchDto> showMainBranches(String mainDealerID, String region, String area) {
		Set<BranchDto> notContainMainBranches = new LinkedHashSet<BranchDto>();
		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<Branch> allBranches = branchRepository.findByState(mainDealer.getState());
		List<Branch> mainBranches = mainDealer.getMainBranches();

		/** e.getMainDealer==null */ // To Get Non Use Dealer

		if (region.equalsIgnoreCase("null") & area.equalsIgnoreCase("null")) {
			allBranches.stream().forEach(e -> {
				if (!mainBranches.contains(e)) {
					BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
							.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
					notContainMainBranches.add(branchDto);
				}
			});
			return notContainMainBranches;
		} else if (!region.equalsIgnoreCase("null") & area.equalsIgnoreCase("null")) {
			allBranches.stream().filter(e -> e.getRegion().equalsIgnoreCase(region)).forEach(e -> {
				if (!mainBranches.contains(e)) {
					BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
							.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
					notContainMainBranches.add(branchDto);
				}
			});
			return notContainMainBranches;
		} else {// (!region.equalsIgnoreCase("null")&& !area.equalsIgnoreCase("null"))
			allBranches.stream()
					.filter(e -> e.getArea().equalsIgnoreCase(area) && e.getRegion().equalsIgnoreCase(region))
					.forEach(e -> {
						if (!mainBranches.contains(e)) {
							BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID())
									.branchName(e.getBranchName()).state(e.getState()).area(e.getArea())
									.region(e.getRegion()).build();
							notContainMainBranches.add(branchDto);
						}
					});
			return notContainMainBranches;
		}
	}

	@Override
	public Set<BranchDto> showAvaliableMainBranches(String mainDealerID) {
		Set<BranchDto> branchs = new LinkedHashSet<BranchDto>();
		MainDealer mainDealer = mainDealerRepository.findById(mainDealerID).get();
		List<Branch> mainBranches = mainDealer.getMainBranches();
		mainBranches.stream().forEach(e -> {
			BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
					.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
			branchs.add(branchDto);
		});
		return branchs;
	}

	// ===========================

	@Override
	public Set<BranchDto> showSubBranches(String subDealearID) {
		Set<BranchDto> notContainMainBranches = new LinkedHashSet<BranchDto>();

		SubDealer subDealer = subDealerRepository.findById(subDealearID).get();
		List<Branch> allBranches = branchRepository.findByState(subDealer.getState());
		List<Branch> subBranches = subDealer.getSubBranches();

		if (!allBranches.isEmpty()) {
			allBranches.stream().forEach(e -> {
				if (!subBranches.contains(e)) {
					BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
							.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
					notContainMainBranches.add(branchDto);
				}
			});
		}
		return notContainMainBranches;
	}

	@Override
	public Set<BranchDto> showSubBranches(String subDealerID, String region, String area) {
		Set<BranchDto> notContainMainBranches = new LinkedHashSet<BranchDto>();
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
		List<Branch> allBranches = branchRepository.findByState(subDealer.getState());
		List<Branch> subBranches = subDealer.getSubBranches();

		if (region.equalsIgnoreCase("null") & area.equalsIgnoreCase("null")) {
			allBranches.stream().forEach(e -> {
				if (!subBranches.contains(e)) {
					BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
							.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
					notContainMainBranches.add(branchDto);
				}
			});
			return notContainMainBranches;
		} else if (!region.equalsIgnoreCase("null") & area.equalsIgnoreCase("null")) {
			allBranches.stream().filter(e -> e.getRegion().equalsIgnoreCase(region)).forEach(e -> {
				if (!subBranches.contains(e)) {
					BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
							.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
					notContainMainBranches.add(branchDto);
				}
			});
			return notContainMainBranches;
		} else {// (!region.equalsIgnoreCase("null")&& !area.equalsIgnoreCase("null"))
			allBranches.stream()
					.filter(e -> e.getArea().equalsIgnoreCase(area) && e.getRegion().equalsIgnoreCase(region))
					.forEach(e -> {
						if (!subBranches.contains(e)) {
							BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID())
									.branchName(e.getBranchName()).state(e.getState()).area(e.getArea())
									.region(e.getRegion()).build();
							notContainMainBranches.add(branchDto);
						}
					});
			return notContainMainBranches;
		}
	}

	@Override
	public Set<BranchDto> showAvaliableSubBranches(String subDealerID) {
		Set<BranchDto> containBranches = new LinkedHashSet<>();
		List<Branch> subBranches = subDealerRepository.findById(subDealerID).get().getSubBranches();
		subBranches.stream().forEach(e -> {
			BranchDto branchDto = BranchDto.builder().branchID(e.getBranchID()).branchName(e.getBranchName())
					.state(e.getState()).area(e.getArea()).region(e.getRegion()).build();
			containBranches.add(branchDto);
		});
		return containBranches;
	}

	/**
	 * @API For Added All Branches Base on MainDealer
	 */
	@Override
	public String addAllMainBranches(String mainDealerID, String subDealerID) {
		List<Branch> mainBranches = mainDealerRepository.findById(mainDealerID).get().getMainBranches();
		if (!mainBranches.isEmpty()) {
			SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
			List<Branch> subBranches = subDealer.getSubBranches();
			mainBranches.stream().forEach(e -> {
				if (!subBranches.contains(e))
					subBranches.add(e);
			});
			subDealer.setSubBranches(subBranches);
			subDealerRepository.save(subDealer);
			return "Sucessfully Added";
		}
		return "Not Added Sucessfully";
	}

	@Override
	public String addSubBranches(String subDealerID, List<BranchDto> branchs) {
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
		List<Branch> subBranches = subDealer.getSubBranches();
		if (!branchs.isEmpty()) {
			branchs.stream().forEach(e -> {
				Branch branch = branchRepository.findById(e.getBranchID()).get();
				if (!subBranches.contains(branch)) {
					subBranches.add(branch);
				}
			});
			subDealer.setSubBranches(subBranches);
			subDealerRepository.save(subDealer);
			return "Sucessfully Added";
		}
		return "Not Added Sucessfully";
	}

	@Override
	public Boolean removeSubBranches(String subDealerID, List<BranchDto> branchs) {
		SubDealer subDealer = subDealerRepository.findById(subDealerID).get();
		List<Branch> subBranches = subDealer.getSubBranches();
		if (!branchs.isEmpty()) {
			branchs.stream().forEach(e -> {
				Branch branch = branchRepository.findById(e.getBranchID()).get();
				if (subBranches.contains(branch))
					subBranches.remove(branch);

			});
			subDealer.setSubBranches(subBranches);
			subDealerRepository.save(subDealer);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
