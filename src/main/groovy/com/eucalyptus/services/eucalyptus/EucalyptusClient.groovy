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

package com.eucalyptus.services.eucalyptus

import javax.xml.stream.events.XMLEvent
import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.AmazonWebServiceRequest
import com.amazonaws.ClientConfiguration
import com.amazonaws.DefaultRequest
import com.amazonaws.Request
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.transform.Marshaller
import com.amazonaws.transform.StaxUnmarshallerContext
import com.amazonaws.transform.Unmarshaller
import com.eucalyptus.services.eucalyptus.model.DescribeInstanceTypesRequest
import com.eucalyptus.services.eucalyptus.model.DescribeInstanceTypesRequestMarshaller
import com.eucalyptus.services.eucalyptus.model.DescribeInstanceTypesResult
import com.eucalyptus.services.eucalyptus.model.DescribeInstanceTypesResultStaxUnmarshaller

/**
 * @todo doc
 * @author chris grzegorczyk <grze@eucalyptus.com>
 */
class EucalyptusClient extends AmazonEC2Client {
  
  public static EucalyptusClient create( ) {
    return new EucalyptusClient( );
  }
  
  private EucalyptusClient( ) {
    this( new DefaultAWSCredentialsProviderChain(), new ClientConfiguration() );
  }
  
  private EucalyptusClient( AWSCredentials awsCredentials ) {
    this( new DefaultAWSCredentialsProviderChain(awsCredentials), new ClientConfiguration());
  }
  
  private EucalyptusClient( AWSCredentialsProvider awsCredentialsProvider ) {
    this( awsCredentialsProvider, new ClientConfiguration());
  }
  
  private EucalyptusClient( ClientConfiguration clientConfiguration ) {
    this( new DefaultAWSCredentialsProviderChain(), clientConfiguration );
  }
  
  private EucalyptusClient( AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration ) {
    super( awsCredentialsProvider, clientConfiguration );
  }
  
  public DescribeInstanceTypesResult describeInstanceTypes(DescribeInstanceTypesRequest describeInstanceTypesRequest)
  throws AmazonServiceException, AmazonClientException {
    Request<DescribeInstanceTypesRequest> request = new DescribeInstanceTypesRequestMarshaller().marshall(describeInstanceTypesRequest);
    return super.invoke(request, new DescribeInstanceTypesResultStaxUnmarshaller() );
  }
  
  public DescribeInstanceTypesResult describeInstanceTypes() {
    return describeInstanceTypes( new DescribeInstanceTypesRequest( ) );
  }
}

class EucalyptusWebServiceRequestCategory {
  public static String getActionParameter( AmazonWebServiceRequest self ) {
    return self.class.simpleName.replaceAll( "Request\$", "" );
  }
}

class EucalyptusRequestMarshaller<T extends AmazonWebServiceRequest> implements Marshaller<Request<T>,T> {
  
  public Request<T> marshall( T input ) throws Exception {
    if (input == null) {
      throw new AmazonClientException("Invalid argument passed to marshall(...)");
    }
    
    Request<T> request = new DefaultRequest<T>(input, "Eucalyptus");
    use(EucalyptusWebServiceRequestCategory) {
      request.addParameter("Action", "${input.getActionParameter( )}" );
    }
    request.addParameter("Version", "eucalyptus");
    
    return request;
  }
}

class Types {
  static generic = { target, context, depth, name, marshaller ->
    if(context.testExpression( name, depth )) {
      marshaller.call(target, name, context);
      return true;
    }
    false;
  }
  static item = { target, name, context, type ->
    def res = type.newInstance();
    res.unmarshall( context )
    target[name.replaceAll('/.*$','')].add(res);
  }
  static string = { target, name, context ->
    target[name]=context.readText( )
  }
  static integer = { target, name, context ->
    target[name]=new Integer(context.readText( ));
  }
}

class UnmarshallerCategory implements Unmarshaller<Object, StaxUnmarshallerContext> {
  
  def unmarshaller() { return { v -> true }; }
  
  public Object unmarshall( StaxUnmarshallerContext context ) throws Exception {
    int originalDepth = context.getCurrentDepth();
    int targetDepth = originalDepth + 1;
    
    
    if (context.isStartOfDocument()) targetDepth += 1;
    
    
    while (true) {
      XMLEvent xmlEvent = context.nextEvent();
      if (xmlEvent.isEndDocument()) return this;
      
      if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
        def m = Types.generic.curry( this, context, targetDepth  );
        unmarshaller().call( m );
      } else if (xmlEvent.isEndElement()) {
        if (context.getCurrentDepth() < originalDepth) {
          return this;
        }
      }
    }
    return this;
  }
}