package com.gyh.base.sharding;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author dingyubin3
 * @date 2020/5/21 14:39
 * Description:分表策略
 */
public class OrgBudgetShardingAlgorithm implements PreciseShardingAlgorithm, RangeShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        String target = shardingValue.getValue().toString();
        return shardingValue.getLogicTableName() + "_" + target.substring(target.lastIndexOf("_") + 1, target.lastIndexOf("_") + 5);
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Integer> shardingValue) {
        Collection<String> availableList = new ArrayList<>();
        Range valueRange = shardingValue.getValueRange();
        for (String target : availableTargetNames) {
            Integer shardValue = Integer.parseInt(target.substring(target.lastIndexOf("_") + 1, target.lastIndexOf("_") + 5));
            if (valueRange.hasLowerBound()) {
                String lowerStr = valueRange.lowerEndpoint().toString();
                Integer start = Integer.parseInt(lowerStr.substring(0, 4));
                if (start - shardValue > 0) {
                    continue;
                }
            }
            if (valueRange.hasUpperBound()) {
                String upperStr = valueRange.upperEndpoint().toString();
                Integer end = Integer.parseInt(upperStr.substring(0, 4));
                if (end - shardValue < 0) {
                    continue;
                }
            }
            availableList.add(target);
        }
        return availableList;
    }


}
