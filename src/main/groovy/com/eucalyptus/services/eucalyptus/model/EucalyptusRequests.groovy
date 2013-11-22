/*************************************************************************
 * Copyright 2009-2012 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Software License Agreement (BSD License)
 *
 *   Copyright (c) 2008, Regents of the University of California
 *   All rights reserved.
 *
 *   Redistribution and use of this software in source and binary forms,
 *   with or without modification, are permitted provided that the
 *   following conditions are met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the
 *     distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *   ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 *   THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 *   COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 *   AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 *   IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 *   SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 *   WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 *   REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 *   IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 *   NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.services.eucalyptus.model

import javax.xml.stream.events.XMLEvent
import com.amazonaws.AmazonWebServiceRequest
import com.amazonaws.Request
import com.amazonaws.services.ec2.AmazonEC2
import com.amazonaws.transform.Marshaller
import com.amazonaws.transform.StaxUnmarshallerContext
import com.amazonaws.transform.Unmarshaller
import com.eucalyptus.services.EucalyptusServices
import com.eucalyptus.services.eucalyptus.EucalyptusRequestMarshaller
import com.eucalyptus.services.eucalyptus.UnmarshallerCategory

class DescribeInstanceTypesRequest extends AmazonWebServiceRequest {
  Boolean verbose;
  Boolean availability;
  List<String> instanceTypes;
  private DescribeInstanceTypesRequest( availability=false, verbose=false, instanceTypes=[]) {
    this.verbose = verbose;
    this.availability = availability;
    this.instanceTypes = instanceTypes;
  }
}

class DescribeInstanceTypesResult {
  List<InstanceType> instanceTypes= [];
}

class DescribeInstanceTypesRequestMarshaller
extends EucalyptusRequestMarshaller<DescribeInstanceTypesRequest>
implements Marshaller<Request<DescribeInstanceTypesRequest>, DescribeInstanceTypesRequest> {
  
  Request<DescribeInstanceTypesRequest> marshall( DescribeInstanceTypesRequest input ) throws Exception {
    Request<DescribeInstanceTypesRequest> request = super.marshall( input );
    request.addParameter("Action", "DescribeVmTypes");
    request.setEndpoint( EucalyptusServices.getEndpoint(AmazonEC2.class) );
    
    input.getInstanceTypes().eachWithIndex{ item, index ->
      request.addParameter( "VmTypes." + index, item )
    }
    request.addParameter( "Availability", "${input.getAvailability( )}" )
    request.addParameter( "Verbose", "${input.getVerbose( )}" )
    return request
  }
}

class DescribeInstanceTypesResultStaxUnmarshaller implements Unmarshaller<DescribeInstanceTypesResult, StaxUnmarshallerContext> {
  public DescribeInstanceTypesResult unmarshall( StaxUnmarshallerContext context ) throws Exception {
    DescribeInstanceTypesResult describeInstanceTypesResult = new DescribeInstanceTypesResult();
    int originalDepth = context.getCurrentDepth();
    int targetDepth = originalDepth + 1;
    
    
    if (context.isStartOfDocument()) targetDepth += 1;
    
    
    while (true) {
      XMLEvent xmlEvent = context.nextEvent();
      if (xmlEvent.isEndDocument()) return describeInstanceTypesResult;
      
      if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
        if (context.testExpression("vmTypeDetails/item", targetDepth)) {
          def res = new InstanceType();
          res.unmarshall( context );
          describeInstanceTypesResult.getInstanceTypes().add(res);
          continue;
        }
      } else if (xmlEvent.isEndElement()) {
        if (context.getCurrentDepth() < originalDepth) {
          return describeInstanceTypesResult;
        }
      }
    }
  }
}
