/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.application.config.plugin;


import org.pf4j.spring.ExtensionsInjector;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * @author Stefan Spiska
 */
public class EhrBasePluginManager extends SpringPluginManager {

  public EhrBasePluginManager(PluginManagerProperties properties) {
    super(properties.getPluginDir());
  }

  private boolean init = false;

  @Override
  public void init() {
    // Plugins will be initialised in initPlugins
  }

  public void initPlugins() {


    if (!init) {

      startPlugins();

      AbstractAutowireCapableBeanFactory beanFactory =
          (AbstractAutowireCapableBeanFactory)
              getApplicationContext().getAutowireCapableBeanFactory();
      ExtensionsInjector extensionsInjector = new ExtensionsInjector(this, beanFactory);
      extensionsInjector.injectExtensions();
      init = true;
    }
  }
}