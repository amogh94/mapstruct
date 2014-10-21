/**
 *  Copyright 2012-2014 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.test.reverse;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Sjaak Derksen
 */
@Mapper
public interface SourceTargetMapperErroneouslyAnnotated {

    SourceTargetMapperErroneouslyAnnotated INSTANCE = Mappers.getMapper( SourceTargetMapperErroneouslyAnnotated.class );

    @Mappings({
        @Mapping(source = "stringPropX", target = "stringPropY"),
        @Mapping(source = "integerPropX", target = "integerPropY"),
        @Mapping(source = "propertyToIgnoreDownstream", target = "propertyNotToIgnoreUpstream")
    })
    Target forward(Source source);

    @InheritInverseConfiguration(name = "forward")
    @Mappings({
        @Mapping(target = "someConstantDownstream", constant = "test"),
        @Mapping(source = "propertyToIgnoreDownstream", ignore = true)
    })
    Source reverse(Target target);

    @InheritInverseConfiguration(name = "reverse")
    @Mappings({
        @Mapping(source = "stringPropX", target = "stringPropY"),
        @Mapping(source = "integerPropX", target = "integerPropY"),
        @Mapping(source = "propertyToIgnoreDownstream", target = "propertyNotToIgnoreUpstream")
    })
    Target forward2(Source source);
}