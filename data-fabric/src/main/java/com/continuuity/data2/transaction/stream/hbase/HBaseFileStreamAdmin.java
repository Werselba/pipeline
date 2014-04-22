/*
 * Copyright 2014 Continuuity,Inc. All Rights Reserved.
 */
package com.continuuity.data2.transaction.stream.hbase;

import com.continuuity.common.conf.CConfiguration;
import com.continuuity.data2.transaction.stream.AbstractFileStreamAdmin;
import com.google.inject.Inject;
import org.apache.twill.filesystem.LocationFactory;

/**
 * A file based {@link com.continuuity.data2.transaction.stream.StreamAdmin} that uses HBase for maintaining
 * consumer state information.
 */
public final class HBaseFileStreamAdmin extends AbstractFileStreamAdmin {

  @Inject
  HBaseFileStreamAdmin(LocationFactory locationFactory, CConfiguration cConf) {
    super(locationFactory, cConf);
  }
}
