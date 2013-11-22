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

package com.eucalyptus.services.eucalyptus.tests;

import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult
import com.amazonaws.services.ec2.model.SecurityGroup
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @todo doc
 * @author chris grzegorczyk <grze@eucalyptus.com>
 */
class EucalyptusAWSJavaSDKTest extends AbstractEucalyptusClientTest {
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeSecurityGroups()}.
   */
  @Test
  void testDescribeSecurityGroupsNoArgs() {
    DescribeSecurityGroupsResult result = client.describeSecurityGroups( );
    result.getSecurityGroups( ).each{ SecurityGroup g -> 
      println "security group: ${g.getGroupName( )} ${g.getOwnerId( )} ${g.getDescription( )}"
    }
  }

  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeVolumes()}.
   */
  @Test
  public void testDescribeVolumes( ) {
    fail( "Not yet implemented" );
  }
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeInstances()}.
   */
  @Test
  public void testDescribeInstances( ) {
    fail( "Not yet implemented" );
  }
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeImages()}.
   */
  @Test
  public void testDescribeImages( ) {
    fail( "Not yet implemented" );
  }
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeTags()}.
   */
  @Test
  public void testDescribeTags( ) {
    fail( "Not yet implemented" );
  }
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeAddresses()}.
   */
  @Test
  public void testDescribeAddresses( ) {
    fail( "Not yet implemented" );
  }
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeKeyPairs()}.
   */
  @Test
  public void testDescribeKeyPairs( ) {
    fail( "Not yet implemented" );
  }
  
  /**
   * Test method for {@link com.amazonaws.services.ec2.AmazonEC2Client#describeSnapshots()}.
   */
  @Test
  public void testDescribeSnapshots( ) {
    fail( "Not yet implemented" );
  }
  
}
