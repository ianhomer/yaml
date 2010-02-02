/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2010 bemoko 
 */
package yaml

class YamlReader extends com.bemoko.live.platform.mwc.plugins.AbstractPlugin {
  def data

  /**
   * Load the yaml data
   */
  void initialise(Map p) {
    def resource = findResource(p.filepath)
    if (!resource) throw new Exception("Can't find ${p.filepath}")
    def inputStream
    try {
      inputStream = new FileInputStream(new File(resource))
      data = org.ho.yaml.Yaml.load(
        new BufferedReader(
          new InputStreamReader(inputStream, p.encoding ?: 'UTF-8')
        )
      )
    } finally {
      org.apache.commons.io.IOUtils.closeQuietly(inputStream)
    }
  }
    
  /**
   * Get accessor - direct to YAML data object
   */
  def get(key) { 
    data[key] 
  }  
}