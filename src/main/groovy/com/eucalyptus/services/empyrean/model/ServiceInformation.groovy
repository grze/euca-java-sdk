package com.eucalyptus.services.empyrean.model

import com.eucalyptus.services.InspectToStringMixin
import com.eucalyptus.services.eucalyptus.Types
import com.eucalyptus.services.eucalyptus.UnmarshallerCategory
import com.eucalyptus.services.eucalyptus.model.InstanceTypeZoneStatus

class ServiceId {
  String partition;
  String name;
  String type;
  String fullName
  String uri;
}

class ServiceStatusDetail {
  String   severity;
  String   uuid;
  String   message;
  String   serviceFullName;
  String   serviceName;
  String   serviceHost;
  String   stackTrace;
  String   timestamp;
  
  public String toString( ) {
    return "${this.timestamp} ${this.severity} ${this.serviceFullName} ${this.serviceName} ${this.serviceHost} ${this.message}";
  }
}

@Mixin([UnmarshallerCategory,InspectToStringMixin,ServiceInformationUnmarshaller])
class ServiceInformation {
  ServiceId serviceId;
  String localState;
  List<ServiceStatusDetail> statusDetails = []
}

class ServiceInformationUnmarshaller {
  def unmarshaller( ) {
    return { m ->
      ["name"].any{ m.call( it, Types.string ) } ||
      ["cpu", "disk", "memory"].any{ m.call( it, Types.integer ) } ||
      ["availability/item"].any{ m.call( it, Types.item.rcurry(InstanceTypeZoneStatus.class) ) }
    }
  }

}