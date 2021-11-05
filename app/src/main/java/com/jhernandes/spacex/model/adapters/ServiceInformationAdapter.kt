package com.jhernandes.spacex.model.adapters

import com.jhernandes.spacex.model.ServiceInformationModel
import com.jhernandes.spacex.repository.model.InfoResponse
import java.text.DecimalFormat


class ServiceInformationAdapter {

    private val valuationFormat = DecimalFormat("#,###.##")
    private val employeesFormat = DecimalFormat("#,###")

    fun mapFromService(info: InfoResponse) =
        ServiceInformationModel(
            companyName = info.name,
            founderName = info.founder,
            year = info.founded.toString(),
            launchSites = info.launchSites.toString(),
            employees = employeesFormat.format(info.employees),
            valuation = valuationFormat.format(info.valuation)
        )

}
